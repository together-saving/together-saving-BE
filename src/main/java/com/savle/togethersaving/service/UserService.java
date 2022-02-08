package com.savle.togethersaving.service;


import com.savle.togethersaving.dto.comment.CreateCommentDto;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.entity.*;

import com.savle.togethersaving.repository.UserRepository;

import com.savle.togethersaving.repository.*;
import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeUserRepository challengeUserRepository;
    private final AccountRepository accountRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final CommentRepository commentRepository;
    private final TagService tagService;



    @Transactional
    public void addComment(Long userId, Long challengeId, CreateCommentDto createCommentDto){


        User user = userRepository.getUserByUserId(userId);
        Challenge challenge = challengeRepository.getByChallengeId(challengeId);

        ChallengeComment comment = ChallengeComment.createComment(user,challenge,createCommentDto.getContent());
        ChallengeComment savedComment = commentRepository.save(comment);

        savedComment.changeCommentListOfUser(user);
        savedComment.changeCommentListOfChallenge(challenge);

    }

    public List<ResponseMyChallengeDto> getMyParticipatingChallenges(Long userId,Pageable pageable) {
        List<ChallengeUser> challengeUserList = challengeUserRepository.findAllByUser_UserId(userId, pageable);

        return challengeUserList.stream()
                .map(cu -> mapToResponseMyChallengeDto(cu.getChallenge()))
                .collect(Collectors.toList());

    }

    private ResponseMyChallengeDto mapToResponseMyChallengeDto(Challenge challenge) {
        ResponseMyChallengeDto dto = ResponseMyChallengeDto.toDto(challenge);
        dto.setTags(tagService.tagsOf(challenge));
        return dto;
    }

    public User getUserByUserId(Long userId) {

        return userRepository.getUserByUserId(userId);
    }


    @Transactional
    public void saveMoney(Long userId, Long challengeId, CreateSavingsDto createSavingDto) {
        User user = userRepository.getUserByUserId(userId);
        Long amount = createSavingDto.getSavingAmount();


        Account sendAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);

        Account receiveAccount = null;

        if (sendAccount.getBalance() - amount >= 0) {

            Challenge challenge = challengeRepository.getByChallengeId(challengeId);

            receiveAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.CMA);

            sendAccount.withdraw(amount);
            receiveAccount.deposit(amount);

            TransactionLog transactionLog = TransactionLog.builder()
                    .challenge(challenge)
                    .amount(createSavingDto.getSavingAmount())
                    .sendAccount(sendAccount)
                    .receiveAccount(receiveAccount)
                    .build();

            TransactionLog savedTransactionLog = transactionLogRepository.save(transactionLog);
            savedTransactionLog.addSendAccountLog(sendAccount);
            savedTransactionLog.addReceiveAccountLog(receiveAccount);
            savedTransactionLog.addChallengeLog(challenge);


          ChallengeUser challengeUser = challengeUserRepository.
                  findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challenge.getChallengeId(),user.getUserId());
           challengeUser.addBalance(amount);
        }
    }


    public User createUser(final User user) {

        final String email = user.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }
}



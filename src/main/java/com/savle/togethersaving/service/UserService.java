package com.savle.togethersaving.service;


import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.UserRepository;
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
    private final ChallengeService challengeService;
    private final ChallengeUserService challengeUserService;
    private final AccountService accountService;
    private final TransactionLogService transactionLogService;
    private final TagService tagService;


    public List<ResponseMyChallengeDto> getMyParticipatingChallenges(Long userId, Pageable pageable) {
        User user = getUserByUserId(userId);
        List<ChallengeUser> challengeUserList = challengeUserService.getChallengeUser(user, pageable);

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

        return userRepository.getById(userId);
    }

    @Transactional
    public ResponseSavingsDto saveMoney(Long userId, Long challengeId, CreateSavingsDto createSavingDto) {

        Long amount = createSavingDto.getChallengePayment();

        // 유저id로 physical 계좌를 찾기
        Account sendAccount = accountService.findAccount(userId, AccountType.PHYSICAL);

        Account receiveAccount = null;
        // physical 계좌에 저축할 돈보다 돈이 많은지 검사.
        if (sendAccount.getBalance() - amount >= 0) {

            //해당 챌린지 조회
            Challenge challenge = challengeService.getChallengeByChallengeId(challengeId);
            // 받을 계좌 조회
            receiveAccount = accountService.findAccount(userId, AccountType.CMA);

            sendAccount.withdraw(amount);
            receiveAccount.deposit(amount);
            //거래 내역 저장
            TransactionLog transactionLog = TransactionLog.builder()
                    .challenge(challenge)
                    .amount(createSavingDto.getChallengePayment())
                    .sendAccount(sendAccount)
                    .receiveAccount(receiveAccount)
                    .build();

            transactionLogService.saveTransaction(transactionLog);
        }

        return ResponseSavingsDto
                .builder()
                .amount(amount)
                .sendAccountNumber(sendAccount.getAccountNumber())
                .sendAccountBankName(sendAccount.getBankName())
                .receiveAccountNumber(receiveAccount.getAccountNumber())
                .receiveAccountBankName(receiveAccount.getBankName())
                .build();
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



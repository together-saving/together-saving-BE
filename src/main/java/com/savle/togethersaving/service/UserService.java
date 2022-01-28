package com.savle.togethersaving.service;


import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.*;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeUserService challengeUserService;
    private final ChallengeUserRepository challengeUserRepository;
    private final AccountRepository accountRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final TagService tagService;


    public List<ResponseMyChallengeDto> getMyParticipatingChallenges(Long userId,Pageable pageable) {
        User user = getUserByUserId(userId);
        List<ChallengeUser> challengeUserList = challengeUserService.getChallengeUser(user,pageable);

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
    public void saveMoney(Long userId, Long challengeId, CreateSavingsDto createSavingDto) {
        User user = userRepository.getUserByUserId(userId);
        Long amount = createSavingDto.getSavingAmount();

        // 유저id로 physical 계좌를 찾기
        Account sendAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);

        Account receiveAccount = null;
        // physical 계좌에 저축할 돈보다 돈이 많은지 검사.
        if (sendAccount.getBalance() - amount >= 0) {

            //해당 챌린지 조회
            Challenge challenge = challengeRepository.getById(challengeId);

            // 받을 계좌 조회
            receiveAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.CMA);

            sendAccount.withdraw(amount);
            receiveAccount.deposit(amount);
            //거래 내역 저장
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


          ChallengeUser challengeUser = challengeUserRepository.getById(new ChallengeUserPK(challenge.getChallengeId(),user.getUserId()));
           challengeUser.addBalance(amount);
        }
    }

}



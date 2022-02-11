package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.saving.SavingDetailDto;
import com.savle.togethersaving.dto.saving.SavingRankingDto;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingService {
    private final AccountRepository accountRepository;
    private final ChallengeUserRepository challengeUserRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final UserRepository userRepository;
    private final ChallengeCountRepository challengeCountRepository;
    private final ChallengeRepository challengeRepository;


    public SavingStatusDto getSavingStatus(Long userId, Long challengeId, String period, Pageable pageable) {
        Account account = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);
        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challengeId,userId);
        List<TransactionLog> transactionLogs = null;

        switch (period) {
            case "today" :
                transactionLogs = transactionLogRepository.getSavingHistorys(userId, challengeId, 0 , pageable);
                break;
            case "1week" :
                transactionLogs = transactionLogRepository.getSavingHistorys(userId, challengeId, 7 , pageable);
                break;
            case "1month" :
                transactionLogs = transactionLogRepository.getSavingHistorys(userId, challengeId, 30 , pageable);
                break;
            case "3month" :
                transactionLogs = transactionLogRepository.getSavingHistorys(userId, challengeId, 90 , pageable);
                break;
        }
        assert transactionLogs != null;
        return SavingStatusDto.of(account,challengeUser,transactionLogs);
    }

    public SavingDetailDto getSavingDetail(Long userId, Long challengeId) {
        User user = userRepository.getUserByUserId(userId);
        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challengeId, userId);
        ChallengeCount challengeCount = challengeCountRepository.getChallengeCountByChallengeId(challengeId);
        Challenge challenge = challengeRepository.getByChallengeId(challengeId);

        Integer savingRate =
                calculateSavingRatio(Math.toIntExact(challengeUser.getAccumulatedBalance()),challengeCount.getMaxCount(),Math.toIntExact(challenge.getPayment()));

        Integer successCount =
                transactionLogRepository.getSuccessCount(userId, challengeId);

        return SavingDetailDto.builder()
                .accumualtedAmount(challengeUser.getAccumulatedBalance())
                .remainCount(challengeCount.getRemainCount())
                .savingRate(savingRate)
                .successCount(successCount)
                .nickname(user.getNickname())
                .failureCount(challengeCount.getCurrentCount() - successCount)
                .thumbnail(user.getProfilePicture())
        .build();
    }

    public List<SavingRankingDto> getSavingRanking(Long challengeId) {
        List<ChallengeUser> challengeUsers = challengeUserRepository.findAllByChallenge_ChallengeId(challengeId);
        Integer maxCount = challengeCountRepository.getChallengeCountByChallengeId(challengeId).getMaxCount();
        Integer challengePayment = Math.toIntExact(challengeRepository.getByChallengeId(challengeId).getPayment());

        return challengeUsers.stream().map(challengeUser -> {
            SavingRankingDto savingRankingDto = SavingRankingDto.userFrom(challengeUser.getUser());
            savingRankingDto.setSavingRate( calculateSavingRatio(Math.toIntExact(challengeUser.getAccumulatedBalance()),maxCount,challengePayment) );
            return savingRankingDto;
        }).sorted(Comparator.comparing(SavingRankingDto::getSavingRate).reversed()).collect(Collectors.toList());
    }

    public Integer calculateSavingRatio(Integer accumulatedBalance, Integer maxCount, Integer payment) {
        return  (( (accumulatedBalance*100) / ( maxCount * payment )));
    }
}

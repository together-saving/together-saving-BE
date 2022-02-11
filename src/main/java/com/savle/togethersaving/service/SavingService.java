package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.saving.SavingDetailDto;
import com.savle.togethersaving.dto.saving.SavingRankingDto;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public SavingStatusDto getSavingStatus(Long userId, Long challengeId, String period, String ordering) {
        User admin = userRepository.getUserByRole("ADMIN");
        List<Account> adminAccountList = accountRepository.findAllByOwner_UserId(admin.getUserId());
        Account account = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);
        List<TransactionLog> transactionLogs = account.getSendLogList().stream().filter(transactionLog -> {
                    return transactionLog.getSendAccount().getAccountType().equals(AccountType.PHYSICAL) &&
                            !adminAccountList.contains(transactionLog.getReceiveAccount());
                })
                .collect(Collectors.toList());

        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challengeId, userId);

        switch (period) {
            case "today":
                transactionLogs = transactionLogs.stream().filter(transactionLog
                                -> transactionLog.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                        .collect(Collectors.toList());
                break;
            case "1week":
                transactionLogs = transactionLogs.stream().filter(transactionLog ->
                                transactionLog.getCreatedAt().toLocalDate().isAfter(LocalDate.now().minusWeeks(1)))
                        .collect(Collectors.toList());
                break;
            case "1month":
                transactionLogs = transactionLogs.stream().filter(transactionLog ->
                                transactionLog.getCreatedAt().toLocalDate().isAfter(LocalDate.now().minusMonths(1)))
                        .collect(Collectors.toList());
                break;
            case "3month":
                transactionLogs = transactionLogs.stream().filter(transactionLog ->
                                transactionLog.getCreatedAt().toLocalDate().isAfter(LocalDate.now().minusMonths(3)))
                        .collect(Collectors.toList());
                break;
        }

        if (ordering.equals("desc")) {
            transactionLogs = transactionLogs.stream().sorted(Comparator.comparing(TransactionLog::getCreatedAt).reversed()).collect(Collectors.toList());
        } else if (ordering.equals("asc")) {
            transactionLogs = transactionLogs.stream().sorted(Comparator.comparing(TransactionLog::getCreatedAt)).collect(Collectors.toList());
        }

        assert transactionLogs != null;
        return SavingStatusDto.of(account, challengeUser, transactionLogs);
    }


    public SavingDetailDto getSavingDetail(Long userId, Long challengeId) {
        User user = userRepository.getUserByUserId(userId);
        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challengeId, userId);
        ChallengeCount challengeCount = challengeCountRepository.getChallengeCountByChallenge_ChallengeId(challengeId);
        Challenge challenge = challengeRepository.getByChallengeId(challengeId);

        Integer savingRate =
                calculateSavingRatio(Math.toIntExact(challengeUser.getAccumulatedBalance()), challengeCount.getMaxCount(), Math.toIntExact(challenge.getPayment()));

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
        Integer maxCount = challengeCountRepository.getChallengeCountByChallenge_ChallengeId(challengeId).getMaxCount();
        Integer challengePayment = Math.toIntExact(challengeRepository.getByChallengeId(challengeId).getPayment());

        return challengeUsers.stream().map(challengeUser -> {
            SavingRankingDto savingRankingDto = SavingRankingDto.userFrom(challengeUser.getUser());
            savingRankingDto.setSavingRate(calculateSavingRatio(Math.toIntExact(challengeUser.getAccumulatedBalance()), maxCount, challengePayment));
            return savingRankingDto;
        }).sorted(Comparator.comparing(SavingRankingDto::getSavingRate).reversed()).collect(Collectors.toList());
    }

    private Integer calculateSavingRatio(Integer accumulatedBalance, Integer maxCount, Integer payment) {
        return (((accumulatedBalance * 100) / (maxCount * payment)));
    }
}

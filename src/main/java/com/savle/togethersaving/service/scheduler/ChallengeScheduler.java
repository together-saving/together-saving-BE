package com.savle.togethersaving.service.scheduler;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeCount;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.repository.ChallengeCountRepository;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.service.AccountService;
import com.savle.togethersaving.service.ChallengeService;
import com.savle.togethersaving.service.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeScheduler {

    private final ChallengeRepository challengeRepository;
    private final ChallengeUserRepository challengeUserRepository;

    private final AccountService accountService;

    @Scheduled(cron = "0 0 9 * * *")
    void openChallenge () {
        challengeRepository.openChallenge();
    }



    //TODO: transaction
    void refundEndedChallenges() {
        List<Challenge> endedChallenges = challengeRepository.findByEndDate(LocalDate.now().minusDays(1L));
        for (Challenge challenge : endedChallenges) {
            List<ChallengeUser> challengeUsers = challengeUserRepository.findAllByChallenge_ChallengeId(challenge.getChallengeId());
            Long rewardSum = 0L;
            for (ChallengeUser challengeUser : challengeUsers) {
                accountService.refundSavingAmount(challengeUser);
                challengeUser.setAccumulatedBalance(0L);
                //TODO: update?
                rewardSum += executeRefundEntryFee(challengeUser);
            }
            List<ChallengeUser> winners = findWinners(challengeUsers);
            giveReward(winners, rewardSum / winners.size());
            challenge.setIsActive(false);
            //TODO : update?
        }
    }

    private List<ChallengeUser> findWinners(List<ChallengeUser> challengeUsers) {
        List<ChallengeUser> winners = new ArrayList<>();
        for (ChallengeUser challengeUser : challengeUsers) {
            if (challengeUser.getSavingRate() == 100) {
                winners.add(challengeUser);
            }
        }
        return winners;
    }

    private Long executeRefundEntryFee(ChallengeUser challengeUser) {
        Long reward = 0L;
        if (challengeUser.getSavingRate() > 85) {
            accountService.refundEntryFee(challengeUser);
        } else {
            reward += challengeUser.getChallenge().getEntryFee();
        }
        return reward;
    }

    private void giveReward(List<ChallengeUser> winners, long reward) {
        for (ChallengeUser winner : winners) {
            accountService.depositReward(winner, reward);
        }
    }
}

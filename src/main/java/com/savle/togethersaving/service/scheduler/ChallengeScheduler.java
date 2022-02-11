package com.savle.togethersaving.service.scheduler;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeCount;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.repository.ChallengeCountRepository;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.service.ChallengeService;
import com.savle.togethersaving.service.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeScheduler {

    private final ChallengeRepository challengeRepository;
    @Scheduled(cron = "0 0 9 * * *")
    void openChallenge () {
        challengeRepository.openChallenge();
    }
}

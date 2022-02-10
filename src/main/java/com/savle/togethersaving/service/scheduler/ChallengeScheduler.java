package com.savle.togethersaving.service.scheduler;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeScheduler {

    private final ChallengeRepository challengeRepository;

    @Scheduled(cron = "0/5 * * * * *")
    void openChallenge () {
        challengeRepository.openChallenge();
    }
}

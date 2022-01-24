package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;


    public Challenge getChallengeByChallengeId(Long challengeId) {

        return challengeRepository.getById(challengeId);
    }

}

package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeCount;

public class CountFixture {

    public static ChallengeCount createCount(Challenge challenge) {

        return ChallengeCount.builder()
                .challenge(challenge)
                .maxCount(10)
                .remainCount(5)
                .currentCount(5)
                .build();
    }
}

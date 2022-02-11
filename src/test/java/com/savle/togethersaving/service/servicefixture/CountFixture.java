package com.savle.togethersaving.service.servicefixture;

import com.savle.togethersaving.entity.ChallengeCount;

import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.challenge;

public class CountFixture {

    public static ChallengeCount challengeCount;

    public static void createChallengeCount() {
        challengeCount = ChallengeCount.builder()
                .countId(1L)
                .challenge(challenge)
                .maxCount(12)
                .remainCount(5)
                .currentCount(7)
                .build();
    }
}

package com.savle.togethersaving.service.servicefixture;

import com.savle.togethersaving.entity.Count;

import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.challenge;

public class CountFixture {

    public static Count challengeCount;

    public static void createChallengeCount() {
        challengeCount = Count.builder()
                .countId(1L)
                .challenge(challenge)
                .maxCount(12)
                .remainCount(5)
                .currentCount(7)
                .build();
    }
}

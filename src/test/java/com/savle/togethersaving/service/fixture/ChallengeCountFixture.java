package com.savle.togethersaving.service.fixture;

import com.savle.togethersaving.entity.ChallengeCount;

import static com.savle.togethersaving.service.fixture.ChallengeFixture.challenge;

public class ChallengeCountFixture {

    public static ChallengeCount challengeCount;

    public static void createChallengeCount() {
        challengeCount = ChallengeCount.builder()
                .challengeId(challenge.getChallengeId())
                .challenge(challenge)
                .maxCount(12)
                .remainCount(5)
                .currentCount(7)
                .build();
    }
}

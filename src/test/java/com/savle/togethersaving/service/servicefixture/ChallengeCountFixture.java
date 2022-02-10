package com.savle.togethersaving.service.servicefixture;

import com.savle.togethersaving.entity.Count;

import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.challenge;

public class ChallengeCountFixture {

    public static Count challengeCount;

    public static void createChallengeCount() {
        challengeCount = Count.builder()
                .challengeId(challenge.getChallengeId())
                .challenge(challenge)
                .maxCount(12)
                .remainCount(5)
                .currentCount(7)
                .build();
    }
}

package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Count;

public class CountFixture {

    public static Count createCount(Challenge challenge) {

        return Count.builder()
                .challenge(challenge)
                .maxCount(10)
                .remainCount(5)
                .currentCount(5)
                .build();
    }
}

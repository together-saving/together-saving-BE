package com.savle.togethersaving.service.fixture;

import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.ChallengeUserPK;

import static com.savle.togethersaving.service.fixture.ChallengeFixture.challenge;
import static com.savle.togethersaving.service.fixture.UserFixture.user;

public class ChallengeUserFixture {

    public static ChallengeUser challengeUser;
    public static void createChallengeUser() {

        challengeUser = ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(1L, user.getUserId()))
                .accumulatedBalance(5000L)
                .isAutomated(false)
                .challenge(challenge)
                .user(user)
                .build();
    }
}

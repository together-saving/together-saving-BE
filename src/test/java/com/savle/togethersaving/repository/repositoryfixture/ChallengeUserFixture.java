package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.ChallengeUserPK;
import com.savle.togethersaving.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengeUserFixture {

    public static List<ChallengeUser> createChallengeUserList(User user, List<Challenge> challenges) {

        List<ChallengeUser> challengeUserList = challenges.stream()
                .map(challenge -> {
                            return ChallengeUser.builder()
                                    .challengeUserPK(new ChallengeUserPK(challenge.getChallengeId(), user.getUserId()))
                                    .accumulatedBalance(0L)
                                    .isAutomated(false)
                                    .savingRate(0)
                                    .challenge(challenge)
                                    .user(user)
                                    .build();
                        }
                ).collect(Collectors.toList());

        return challengeUserList;
    }

    public static ChallengeUser createChallengeUser(User user, Challenge challenge) {

        return ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(challenge.getChallengeId(), user.getUserId()))
                .accumulatedBalance(0L)
                .isAutomated(false)
                .savingRate(0)
                .challenge(challenge)
                .user(user)
                .build();
    }

    public static ChallengeUser createSavedMoneyChallengeUser(User user, Challenge challenge) {

        return ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(challenge.getChallengeId(), user.getUserId()))
                .accumulatedBalance(1000L)
                .isAutomated(false)
                .savingRate(0)
                .challenge(challenge)
                .user(user)
                .build();
    }
}

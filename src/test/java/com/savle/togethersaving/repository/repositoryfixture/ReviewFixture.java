package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.entity.User;

public class ReviewFixture {

    public static ChallengeReview creteReview(User user, Challenge challenge) {

         return ChallengeReview.builder()
                .reviewer(user)
                .challenge(challenge)
                .content("이거 재미있네요")
                .build();
    }
}

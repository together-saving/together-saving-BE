package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Review;
import com.savle.togethersaving.entity.User;

public class ReviewFixture {

    public static Review creteReview( User user,Challenge challenge) {

         return Review.builder()
                .reviewer(user)
                .challenge(challenge)
                .content("이거 재미있네요")
                .build();
    }
}

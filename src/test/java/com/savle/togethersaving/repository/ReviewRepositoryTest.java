package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class ReviewRepositoryTest extends RepositoryTestUtil {


    @DisplayName("Review Save 성공.")
    @Test
    void reviewSaveTest() {
        createUserAndChallengeSaved();

        ChallengeReview challengeReview = ChallengeReview.builder()
                .reviewer(user)
                .challenge(previousChallenge)
                .content("이거 재미있네요")
                .build();

        reviewRepository.save(challengeReview);
        ChallengeReview savedChallengeReview = reviewRepository.findById(challengeReview.getReviewId()).orElseThrow();


        assertThat(savedChallengeReview.getReviewId()).isEqualTo(challengeReview.getReviewId());
        assertThat(savedChallengeReview.getChallenge().getTitle()).isEqualTo("돈 모으자");
        assertThat(savedChallengeReview.getReviewer().getUserId()).isEqualTo(user.getUserId());
        assertThat(savedChallengeReview.getContent()).isEqualTo("이거 재미있네요");
    }
}
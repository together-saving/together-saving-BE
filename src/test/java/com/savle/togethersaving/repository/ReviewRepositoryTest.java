package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ReviewRepositoryTest extends RepositoryTestUtil {


    @DisplayName("Review Save 성공.")
    @Test
    void reviewSaveTest() {
        createUserAndChallengeSaved();
        ChallengeReview challengeReview = saveOneReview(user, previousChallenge);
        ChallengeReview savedChallengeReview = reviewRepository.findById(challengeReview.getReviewId()).orElseThrow();


        assertThat(savedChallengeReview.getReviewId()).isEqualTo(challengeReview.getReviewId());
        assertThat(savedChallengeReview.getChallenge().getTitle()).isEqualTo("돈 모으자");
        assertThat(savedChallengeReview.getReviewer().getUserId()).isEqualTo(user.getUserId());
        assertThat(savedChallengeReview.getContent()).isEqualTo("이거 재미있네요");
    }

    private ChallengeReview saveOneReview(User user, Challenge challenge) {
        ChallengeReview challengeReview = ChallengeReview.builder()
                .reviewer(user)
                .challenge(challenge)
                .content("이거 재미있네요")
                .build();

        reviewRepository.save(challengeReview);
        return challengeReview;
    }

    @Test
    void getReviewsById() {
        createUserAndChallengeSaved();
        ChallengeReview review = saveOneReview(user, previousChallenge);
        List<ChallengeReview> allReview = reviewRepository.findAllByChallenge_ChallengeId(previousChallenge.getChallengeId());
        assertThat(allReview.size()).isEqualTo(1);
        assertThat(allReview.get(0).getReviewId()).isEqualTo(review.getReviewId());
    }
}
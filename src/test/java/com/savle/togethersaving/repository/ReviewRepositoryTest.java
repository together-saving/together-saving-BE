package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Review;
import com.savle.togethersaving.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.ReviewFixture.creteReview;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("Review Save 성공.")
    @Test
    void reviewSaveTest() {

        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        Review savedReview = reviewRepository.save(creteReview(savedUser, savedChallenge));

        //when
        Review findReview = reviewRepository.findById(savedReview.getReviewId()).orElseThrow();

        //then
        assertThat(findReview.getReviewId()).isEqualTo(savedReview.getReviewId());
        assertThat(findReview.getChallenge().getTitle()).isEqualTo("돈 모으자");
        assertThat(findReview.getReviewer().getUserId()).isEqualTo(savedUser.getUserId());
        assertThat(findReview.getContent()).isEqualTo("이거 재미있네요");
    }


    @Test
    void getReviewsById() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        Review savedReview = reviewRepository.save(creteReview(savedUser, savedChallenge));

        //when
        List<Review> allReview = reviewRepository.findAllByChallenge_ChallengeId(savedChallenge.getChallengeId());

        //then
        assertThat(allReview.size()).isEqualTo(1);
        assertThat(allReview.get(0).getReviewId()).isEqualTo(savedReview.getReviewId());
    }
}
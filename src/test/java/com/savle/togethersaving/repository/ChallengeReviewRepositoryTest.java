package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeReview;
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
class ChallengeReviewRepositoryTest {

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
        ChallengeReview savedChallengeReview = reviewRepository.save(creteReview(savedUser, savedChallenge));

        //when
        ChallengeReview findChallengeReview = reviewRepository.findById(savedChallengeReview.getReviewId()).orElseThrow();

        //then
        assertThat(findChallengeReview.getReviewId()).isEqualTo(savedChallengeReview.getReviewId());
        assertThat(findChallengeReview.getChallenge().getTitle()).isEqualTo("돈 모으자");
        assertThat(findChallengeReview.getReviewer().getUserId()).isEqualTo(savedUser.getUserId());
        assertThat(findChallengeReview.getContent()).isEqualTo("이거 재미있네요");
    }


    @Test
    void getReviewsById() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        ChallengeReview savedChallengeReview = reviewRepository.save(creteReview(savedUser, savedChallenge));

        //when
        List<ChallengeReview> allChallengeReview = reviewRepository.findAllByChallenge_ChallengeId(savedChallenge.getChallengeId());

        //then
        assertThat(allChallengeReview.size()).isEqualTo(1);
        assertThat(allChallengeReview.get(0).getReviewId()).isEqualTo(savedChallengeReview.getReviewId());
    }
}
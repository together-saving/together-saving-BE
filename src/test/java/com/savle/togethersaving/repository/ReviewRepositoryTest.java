package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    protected User user;
    protected Challenge challenge;

    @DisplayName("Review Save 성공.")
    @Test
    void reviewSaveTest() {
        createUserAndChallenge();
        em.flush();
        ChallengeReview challengeReview = ChallengeReview.builder()
                .reviewer(user)
                .challenge(challenge)
                .content("이거 재미있네요")
                .build();

        reviewRepository.save(challengeReview);
        ChallengeReview savedChallengeReview = reviewRepository.findById(1L).orElseThrow();


        assertThat(savedChallengeReview.getReviewId()).isEqualTo(1L);
        assertThat(savedChallengeReview.getChallenge().getChallengeId()).isEqualTo(1L);
        assertThat(savedChallengeReview.getReviewer().getUserId()).isEqualTo(1L);
        assertThat(savedChallengeReview.getContent()).isEqualTo("이거 재미있네요");
    }


    protected void createUserAndChallenge() {

        user = User.builder()
                .email("sheep@naver.com")
                .birth(LocalDate.of(2020, 01, 01))
                .gender(true)
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.com")
                .nickname("NICK")
                .role(Role.USER)
                .point(0L)
                .reward(0L)
                .build();


        challenge = Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(100L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com")
                .build();

        userRepository.save(user);
        challengeRepository.save(challenge);

    }

}
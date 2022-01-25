package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    UserRepository userRepository;

    protected User user;
    protected Challenge challenge;

    @DisplayName("Review Save 성공.")
    @Test
    void reviewSaveTest() {
        createUserAndChallenge();

        ChallengeReview challengeReview = ChallengeReview.builder()
                .reviewer(user)
                .challenge(challenge)
                .content("이거 재미있네요")
                .build();

        reviewRepository.save(challengeReview);
        ChallengeReview savedChallengeReview = reviewRepository.findById(challengeReview.getReviewId()).orElseThrow();


        assertThat(savedChallengeReview.getReviewId()).isEqualTo(challengeReview.getReviewId());
        assertThat(savedChallengeReview.getChallenge().getTitle()).isEqualTo("돈 모으자");
        assertThat(savedChallengeReview.getReviewer().getUserId()).isEqualTo(user.getUserId());
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
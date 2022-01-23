package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Role;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ReviewRepository;
import com.savle.togethersaving.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class TestUtil {

    @Autowired
    protected UserService userService;
    @Autowired
    protected ChallengeService challengeService;
    @Autowired
    protected ReviewService reviewService;
    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ChallengeRepository challengeRepository;


    protected User user;
    protected Challenge challenge;
    protected ReviewCreateDto reviewCreateDto;

    protected void createUserAndChallenge() {

        user = User.builder()
                .userId(1L)
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
                .challengeId(1L)
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

        reviewCreateDto = ReviewCreateDto.builder()
                .challengeId(1L)
                .reviewContent("즐겁네요")
                .build();

        userRepository.save(user);
        challengeRepository.save(challenge);
    }
}

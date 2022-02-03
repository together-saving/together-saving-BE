package com.savle.togethersaving.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTestUtil {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    protected User user;
    protected Challenge challenge1;
    protected Challenge challenge2;
    protected Challenge challenge3;


    void createUserAndChallenge() {

        user = User.builder()
                .userId(1L)
                .email("sheep@naver.com")
                .birth(LocalDate.of(2020, 01, 01))
                .gender(true)
                .password("1234")
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.com")
                .nickname("NICK")
                .role("user")
                .point(0L)
                .reward(0L)
                .build();


        challenge1 = Challenge.builder()
                .challengeId(1L)
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자1")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(1L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(1)
                .thumbnail("http://qweqweqwe.com").build();

        challenge2 = Challenge.builder()
                .challengeId(2L)
                .host(user)
                .startDate(LocalDate.now().minusDays(2L))
                .title("돈 모으자2")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지였다")
                .payment(15000L)
                .members(2L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(2)
                .thumbnail("http://qweqweqwe.com").build();

        challenge3 = Challenge.builder()
                .challengeId(3L)
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자3")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(3L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();

    }

}

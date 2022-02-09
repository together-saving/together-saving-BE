package com.savle.togethersaving.service.fixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.User;

import java.time.LocalDate;

import static com.savle.togethersaving.service.fixture.UserFixture.user;

public class ChallengeFixture {


    public static Challenge challenge;

    public static void createChallenge() {

        challenge =  Challenge.builder()
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
    }

}

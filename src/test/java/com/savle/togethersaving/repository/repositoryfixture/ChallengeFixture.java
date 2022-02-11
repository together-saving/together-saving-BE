package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChallengeFixture {

    public static Challenge createChallenge(User user) {

        return Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(11L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();
    }

    public static List<Challenge> createChallengeList(User user) {

        List<Challenge> challengeList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            challengeList.add(
                    Challenge.builder()
                            .host(user)
                            .startDate(LocalDate.now().plusDays(i))
                            .title("돈 모으자")
                            .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                            .payment((long) i * 1000)
                            .members((long) i)
                            .mode(Mode.FREE)
                            .entryFee(5000L)
                            .period(i)
                            .thumbnail("http://qweqweqwe.com")
                            .build()
            );
        }
        challengeList.add(
                Challenge.builder()
                        .host(user)
                        .startDate(LocalDate.now().minusDays(1))
                        .title("돈 모으자")
                        .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                        .payment(11000L)
                        .members(11L)
                        .mode(Mode.FREE)
                        .entryFee(5000L)
                        .period(11)
                        .thumbnail("http://qweqweqwe.com")
                        .build()
        );

        return challengeList;
    }
}

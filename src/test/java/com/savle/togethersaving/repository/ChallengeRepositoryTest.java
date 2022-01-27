package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;


class ChallengeRepositoryTest extends RepositoryTestUtil {


    @Test
    @DisplayName("아직 시작하지 않은 모든 챌린지들을 조회한다. 성공하는 케이스")
    void beforeStartChallengeTest() {
        //given
        createUserAndChallengeSaved();

        //when
        List<Challenge> challengesByStartDateAfter
                = challengeRepository.findChallengesByStartDateGreaterThan(LocalDate.now());

        //then
        Assertions.assertThat(challengesByStartDateAfter.size()).isEqualTo(12);
    }

    @Test
    @DisplayName("인기있는 모든 챌린지들을 조회한다. 성공하는 케이스")
    void popularChallengeTest() {
        //given
        createUserAndChallengeSaved();

        challengeRepository.save(previousChallenge);
        challengeRepository.save(afterChallenge);
        challengeRepository.save(biggest);

        //when
        List<Challenge> popularChallenges
                = challengeRepository
                .findChallengesByStartDateGreaterThan(LocalDate.now(), PageRequest.of(1, 7, Sort.by("members").descending()));
        //then
        Assertions.assertThat(popularChallenges.get(0).getMembers()).isEqualTo(11L);
    }
}
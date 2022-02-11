package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallengeList;
import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createFinishedChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;


@DataJpaTest
class ChallengeRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    @DisplayName("아직 시작하지 않은 모든 챌린지들을 조회한다. 성공하는 케이스")
    void beforeStartChallengeTest() {
        //given
        User savedUser = userRepository.save(createUser());
        challengeRepository.saveAll(createChallengeList(savedUser));

        //when
        List<Challenge> findChallenges
                = challengeRepository.findChallengesByStartDateGreaterThan(LocalDate.now());

        //then
        Assertions.assertThat(findChallenges.size()).isEqualTo(10);

    }

    @Test
    @DisplayName("인기있는 모든 챌린지들을 조회한다. 성공하는 케이스")
    void popularChallengeTest() {
        //given
        User savedUser = userRepository.save(createUser());
        challengeRepository.saveAll(createChallengeList(savedUser));

        //when
        List<Challenge> popularChallenges
                = challengeRepository
                .findChallengesByStartDateGreaterThan(LocalDate.now(), PageRequest.of(0, 7, Sort.by("members").descending()));
        //then
        Assertions.assertThat(popularChallenges.get(0).getMembers()).isEqualTo(10L);
    }

    @Test
    @DisplayName("종료된 챌린지를 찾는다.")
    void finishedChallengeList() {
        //given
        User savedUser = userRepository.save(createUser());
        challengeRepository.saveAll(createFinishedChallenge(savedUser));

        //when
        List<Challenge> finishedChallenge = challengeRepository.findByEndDate(LocalDate.now().minusDays(1));

        //then
        Assertions.assertThat(finishedChallenge.get(0).getMembers()).isEqualTo(11L);

    }
}
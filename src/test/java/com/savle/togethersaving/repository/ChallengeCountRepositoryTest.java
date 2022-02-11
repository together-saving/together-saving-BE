package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeCount;
import com.savle.togethersaving.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.CountFixture.createCount;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;

@DataJpaTest
class ChallengeCountRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeCountRepository challengeCountRepository;

    @Test
    @DisplayName("챌린지 카운트 레포지토리 테스트")
    void findChallengeCountRepository() {
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        challengeCountRepository.save(createCount(savedChallenge));

        ChallengeCount findChallengeCount = challengeCountRepository.getChallengeCountByChallenge_ChallengeId(savedChallenge.getChallengeId());
        Assertions.assertEquals(findChallengeCount.getMaxCount(),10);
    }
}
package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChallengeCountRepositoryTest {
    @Autowired
    private ChallengeCountRepository challengeCountRepository;

    @Test
    @DisplayName("챌린지 카운트 레포지토리 테스트")
    void findChallengeCountRepository() {
        ChallengeCount challengeCount;
        challengeCount = challengeCountRepository.getChallengeCountByChallengeId(1L);
        Assertions.assertEquals(challengeCount.getMaxCount(),12);
    }
}
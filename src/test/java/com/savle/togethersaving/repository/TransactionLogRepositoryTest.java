package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionLogRepositoryTest {
    @Autowired
    private TransactionLogRepository transactionLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    @Test
    void getSavingHistorys() {
        User user = userRepository.getById(1L);
        Challenge challenge = challengeRepository.getById(1L);
        List<TransactionLog> savingHistorys = transactionLogRepository.getSavingHistorys(user.getUserId(), challenge.getChallengeId());
        Assertions.assertThat(savingHistorys.size()).isEqualTo(1);
    }
}
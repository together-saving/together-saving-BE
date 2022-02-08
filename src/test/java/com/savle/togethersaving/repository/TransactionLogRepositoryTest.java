package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        User user = userRepository.getUserByUserId(1L);
        Challenge challenge = challengeRepository.getByChallengeId(1L);
        PageRequest pageRequest = PageRequest.of(0,1000, Sort.by("created_at").descending());
        List<TransactionLog> savingHistories = transactionLogRepository
                .getSavingHistorys(user.getUserId(), challenge.getChallengeId()
                                   , 5 , pageRequest);
        for (TransactionLog log : savingHistories) {
            Assertions.assertThat(log.getChallenge().getChallengeId()).isEqualTo(1L);
        }

    }

    @Test
    void getSuccessCount() {
        Integer successCount = transactionLogRepository.getSuccessCount(1L,1L);

        Assertions.assertThat(successCount).isEqualTo(15);
    }
}
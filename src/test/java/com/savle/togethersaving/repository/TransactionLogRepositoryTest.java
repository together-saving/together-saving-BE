package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.repositoryfixture.AccountFixture;
import com.savle.togethersaving.repository.repositoryfixture.TransactionLogFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;

@DataJpaTest
class TransactionLogRepositoryTest {

    @Autowired
    private TransactionLogRepository transactionLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private AccountRepository accountRepository;

}
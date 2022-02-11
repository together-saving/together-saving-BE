package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.savle.togethersaving.repository.repositoryfixture.AccountFixture.createFirstSendAccSecondReceiveAcc;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;


@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Test
    void findAccountByOwner_UserIdAndAccountTypeTest() {
        //given
        User savedUser = userRepository.save(createUser());
        accountRepository.saveAll(createFirstSendAccSecondReceiveAcc(savedUser));

        //when
        Account findAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(
                savedUser.getUserId(), AccountType.PHYSICAL);

        //then
        Assertions.assertThat(findAccount.getAccountNumber()).isEqualTo("110-110");
    }

}


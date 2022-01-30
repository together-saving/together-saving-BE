package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;


    @Test
    void findAccountByOwner_UserIdAndAccountTypeTest() {
        Account account = accountRepository.findAccountByOwner_UserIdAndAccountType(1L, AccountType.PHYSICAL);
        Assertions.assertThat(account.getAccountNumber()).isEqualTo("35-12325-23125");
    }

}


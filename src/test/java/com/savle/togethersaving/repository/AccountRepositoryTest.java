package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest extends RepositoryTestUtil {

    @Test
    void findAccountByOwner_UserIdAndAccountTypeTest() {
        Account account = accountRepository.findAccountByOwner_UserIdAndAccountType(1L, AccountType.PHYSICAL);
        Assertions.assertThat(account.getAccountNumber()).isEqualTo("35-12325-23125");
    }
}

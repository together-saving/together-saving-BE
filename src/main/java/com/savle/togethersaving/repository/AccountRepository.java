package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findAccountByOwnerAndAccountType(Long userId,AccountType accountType);
}

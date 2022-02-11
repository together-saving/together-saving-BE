package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Account findAccountByOwner_UserIdAndAccountType(Long userId, AccountType accountType);
    
    List<Account> findAllByOwner_UserId(Long userId);

}

package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public Account findAccount(Long userId,AccountType accountType) {
      return  accountRepository.findAccountByOwner_UserIdAndAccountType(userId, accountType);
    }

}

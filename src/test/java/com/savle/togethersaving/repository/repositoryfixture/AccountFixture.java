package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AccountFixture {

    public static List<Account> createFirstSendAccSecondReceiveAcc(User user) {

        List<Account> accountList = new ArrayList<>();

        Account sendAccount = Account
                .builder()
                .accountNumber("110-110")
                .owner(user)
                .balance(10000L)
                .accountType(AccountType.PHYSICAL)
                .bankName("kakao")
                .thumbnail("http://thumbnail.com")
                .build();

        Account receiveAccount = Account
                .builder()
                .accountNumber("220-220")
                .owner(user)
                .balance(0L)
                .accountType(AccountType.CMA)
                .bankName("kakao-cma")
                .thumbnail("http://thumbnail.com")
                .build();


        accountList.add(sendAccount);
        accountList.add(receiveAccount);

        return accountList;
    }
}

package com.savle.togethersaving.service.fixture;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.User;

import static com.savle.togethersaving.service.fixture.UserFixture.admin;
import static com.savle.togethersaving.service.fixture.UserFixture.user;

public class AccountFixture {

    public static Account sendAccount;
    public static Account receiveAccount;
    public static Account adminAccount;

    public static void createTwoKindsOfUserAccounts() {

        sendAccount = Account
                .builder()
                .accountNumber("110-110")
                .owner(user)
                .balance(10000L)
                .accountType(AccountType.PHYSICAL)
                .bankName("kakao")
                .build();

        receiveAccount = Account
                .builder()
                .accountNumber("220-220")
                .owner(user)
                .balance(0L)
                .accountType(AccountType.CMA)
                .bankName("kakao-cma")
                .build();
    }

    public static void createAdminAccount(){
        adminAccount = Account
                .builder()
                .accountNumber("admin-admin")
                .owner(admin)
                .balance(0L)
                .accountType(AccountType.CMA)
                .bankName("kakao-cma")
                .build();
    }
}

package com.savle.togethersaving.service;


import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.entity.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ServiceTestUtil {

    protected User user;
    protected User admin;
    protected Challenge challenge;

    protected Account receiveAccount;
    protected Account sendAccount;
    protected Account adminReceiveAccount;
    protected TransactionLog saveTransactionLog;
    protected TransactionLog payTransactionLog;
    protected ChallengeUser challengeUser;

    protected ReviewCreateDto reviewCreateDto;
    protected CreateSavingsDto createSavingDto;

    void createUserAndChallenge() {

        user = User.builder()
                .userId(1L)
                .email("sheep@naver.com")
                .birth(LocalDate.of(2020, 01, 01))
                .gender(true)
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.com")
                .nickname("NICK")
                .role(Role.USER)
                .point(0L)
                .reward(0L)
                .password("password")
                .build();

        admin = User.builder()
                .userId(2L)
                .email("admin@naver.com")
                .birth(LocalDate.of(2021, 11, 11))
                .gender(true)
                .phoneNumber("010-5678-1234")
                .profilePicture("http://qweqweqweqwe.com")
                .nickname("Admin-NICK")
                .role(Role.ADMIN)
                .point(0L)
                .reward(0L)
                .password("password")
                .build();

        challenge = Challenge.builder()
                .challengeId(1L)
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(100L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com")
                .build();
    }

    void createTwoKindsOfUserAccountsAndAdminAccount() {

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


        adminReceiveAccount = Account
                .builder()
                .accountNumber("admin-admin")
                .owner(admin)
                .balance(0L)
                .accountType(AccountType.CMA)
                .bankName("kakao-cma")
                .build();

    }

    void createDtos() {
        reviewCreateDto = ReviewCreateDto.builder()
                .challengeId(1L)
                .reviewContent("즐겁네요")
                .build();
        createSavingDto = CreateSavingsDto.builder()
                .challengePayment(5000L)
                .physicalAccountNumber("110-110")
                .cmaAccountNumber("220-220")
                .build();

    }

    void createTransactionLog(){
        saveTransactionLog = TransactionLog.builder()
                .logId(1L)
                .challenge(challenge)
                .amount(createSavingDto.getChallengePayment())
                .sendAccount(sendAccount)
                .receiveAccount(receiveAccount)
                .build();

        payTransactionLog = TransactionLog.builder()
                .logId(1L)
                .challenge(challenge)
                .amount(challenge.getEntryFee())
                .sendAccount(sendAccount)
                .receiveAccount(receiveAccount)
                .build();
    }

    void createChallengeUser(){
        challengeUser = ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(1L,user.getUserId()))
                .accumulatedBalance(0L)
                .isAutomated(false)
                .challenge(challenge)
                .user(user)
                .build();
    }

}
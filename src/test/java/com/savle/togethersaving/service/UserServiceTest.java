package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.TransactionLogRepository;
import com.savle.togethersaving.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;



public class UserServiceTest extends ServiceTestUtil {

    @Mock
    protected AccountService accountService;
    @Mock
    protected ChallengeService challengeService;
    @Mock
    private TransactionLogRepository transactionLogRepository;
    @Mock
    private ChallengeUserService challengeUserService;
    @Mock
    private TagService tagService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TransactionLogService transactionLogService;


    @InjectMocks
    private UserService userService;


    @DisplayName("저축 성공")
    @Test
    void shouldSavingSuccessfully() {

        createUserAndChallenge();
        createTwoKindsOfAccounts();
        createDtos();
        createTransactionLog();

        doReturn(sendAccount)
                .when(accountService).findAccount(user.getUserId(), AccountType.PHYSICAL);

        doReturn(challenge)
                .when(challengeService).getChallengeByChallengeId(challenge.getChallengeId());

        doReturn(receiveAccount)
                .when(accountService).findAccount(user.getUserId(), AccountType.CMA);


        ResponseSavingsDto savingDto = userService.saveMoney(user.getUserId(), challenge.getChallengeId(), createSavingDto);


        assertEquals(savingDto.getAmount(), 5000L);
        assertEquals(savingDto.getSendAccountNumber(), "110-110");
        assertEquals(savingDto.getReceiveAccountNumber(), "220-220");
        assertEquals(receiveAccount.getBalance(), 5000L);
    }


}

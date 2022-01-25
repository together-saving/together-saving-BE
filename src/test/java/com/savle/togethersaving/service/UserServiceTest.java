package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.TransactionLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @InjectMocks
    private UserService userService;


    @DisplayName("SaveReview() -리뷰 저장 성공")
    @Test
    void shouldSavingSuccessfully() {

        createUserAndChallenge();
        createTwoKindsOfAccounts();
        createDtos();

        doReturn(sendAccount)
                .when(accountService).findAccount(user.getUserId(), AccountType.PHYSICAL);

        doReturn(challenge)
                .when(challengeService).getChallengeByChallengeId(challenge.getChallengeId());

        doReturn(receiveAccount)
                .when(accountService).findAccount(user.getUserId(), AccountType.CMA);

        given(transactionLogRepository.save(any(TransactionLog.class))).will(invocation -> TransactionLog.builder()
                .logId(1L)
                .challenge(challenge)
                .amount(createSavingDto.getChallengePayment())
                .sendAccount(sendAccount)
                .receiveAccount(receiveAccount)
                .build());

        ResponseSavingsDto savingDto = userService.saveMoney(user.getUserId(), challenge.getChallengeId(), createSavingDto);


        assertEquals(savingDto.getAmount(), 5000L);
        assertEquals(savingDto.getSendAccountNumber(), "110-110");
        assertEquals(savingDto.getReceiveAccountNumber(), "220-220");
        assertEquals(receiveAccount.getBalance(), 5000L);
    }

}

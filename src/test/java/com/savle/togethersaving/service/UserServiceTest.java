package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.TransactionLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    protected AccountService accountService;
    @Mock
    protected ChallengeService challengeService;
    @Mock
    private TransactionLogRepository transactionLogRepository;

    @InjectMocks
    private UserService userService;

    protected final User user = User.builder()
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
            .build();

    protected final Challenge challenge = Challenge.builder()
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

    protected final Account sendAccount = Account
            .builder()
            .accountNumber("110-110")
            .owner(user)
            .balance(10000L)
            .accountType(AccountType.PHYSICAL)
            .bankName("kakao")
            .build();

    protected final Account receiveAccount = Account
            .builder()
            .accountNumber("220-220")
            .owner(user)
            .balance(0L)
            .accountType(AccountType.CMA)
            .bankName("kakao-cma")
            .build();

    protected final CreateSavingsDto createSavingDto = CreateSavingsDto.builder()
            .challengePayment(5000L)
            .physicalAccountNumber("110-110")
            .cmaAccountNumber("220-220")
            .build();

    @DisplayName("SaveReview() -리뷰 저장 성공")
    @Test
    void shouldSavingSuccessfully() {

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

        ResponseSavingsDto savingDto = userService.saveMoney(user.getUserId(),challenge.getChallengeId(),createSavingDto);


        assertEquals(savingDto.getAmount(), 5000L);
        assertEquals(savingDto.getSendAccountNumber(), "110-110");
        assertEquals(savingDto.getReceiveAccountNumber(), "220-220");
        assertEquals(receiveAccount.getBalance(), 5000L);
    }

}

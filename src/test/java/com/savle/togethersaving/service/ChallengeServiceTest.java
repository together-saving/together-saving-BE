package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

public class ChallengeServiceTest extends ServiceTestUtil{

    @Mock
    protected ChallengeRepository challengeRepository;
    @Mock
    private UserService userService;
    @Mock
    private AccountService accountService;
    @Mock
    private TransactionLogService transactionLogService;

    @InjectMocks
    private ChallengeService challengeService;

    @DisplayName("payForChallenge() - 결제 성공")
    @Test
    void shouldSavedReviewSuccessfully() {
        createUserAndChallenge();
        createTwoKindsOfUserAccountsAndAdminAccount();

        doReturn(admin)
                .when(userService).getAdmin();

        doReturn(sendAccount)
                .when(accountService).findAccount(user.getUserId(), AccountType.PHYSICAL);

        doReturn(receiveAccount)
                .when(accountService).findAccount(admin.getUserId(), AccountType.CMA);

        doReturn(payTransactionLog)
                .when(transactionLogService).saveTransaction(payTransactionLog);

        challengeService.payForChallenge(user.getUserId(),challenge.getChallengeId());
    }
}

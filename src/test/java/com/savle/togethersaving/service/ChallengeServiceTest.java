package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChallengeServiceTest extends ServiceTestUtil{

    @Mock
    protected ChallengeRepository challengeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionLogRepository transactionLogRepository;
    @Mock
    private ChallengeUserRepository challengeUserRepository;

    @InjectMocks
    private ChallengeService challengeService;

    @DisplayName("payForChallenge() - 결제 성공")
    @Test
    void shouldSavedReviewSuccessfully() {
        createUserAndChallenge();
        createTwoKindsOfUserAccountsAndAdminAccount();
        createDtos();
        createTransactionLog();

        doReturn(user)
                .when(userRepository).getUserByUserId(user.getUserId());

        doReturn(admin)
                .when(userRepository).getUserByRole(Role.ADMIN);

        doReturn(challenge)
                .when(challengeRepository).getById(challenge.getChallengeId());

        doReturn(sendAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(user.getUserId(), AccountType.PHYSICAL);

        doReturn(receiveAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(admin.getUserId(), AccountType.CMA);

        doReturn(payTransactionLog).when(transactionLogRepository).save(any(TransactionLog.class));

        challengeService.payForChallenge(user.getUserId(),challenge.getChallengeId());

        verify(transactionLogRepository,times(1)).save(any(TransactionLog.class));
        verify(challengeUserRepository,times(1)).save(any(ChallengeUser.class));


    }


}

package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.savle.togethersaving.service.servicefixture.AccountFixture.*;
import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.challenge;
import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.service.servicefixture.DtoFixture.createSavingDto;
import static com.savle.togethersaving.service.servicefixture.TransactionLogFixture.createTransactionLog;
import static com.savle.togethersaving.service.servicefixture.TransactionLogFixture.payTransactionLog;
import static com.savle.togethersaving.service.servicefixture.UserFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

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
        createUser();
        createAdmin();
        createChallenge();
        createTwoKindsOfUserAccounts();
        createSavingDto();
        createTransactionLog();

        doReturn(user)
                .when(userRepository).getUserByUserId(user.getUserId());

        doReturn(admin)
                .when(userRepository).getUserByRole("ADMIN");

        doReturn(challenge)
                .when(challengeRepository).getByChallengeId(challenge.getChallengeId());

        doReturn(sendAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(user.getUserId(), AccountType.PHYSICAL);

        doReturn(receiveAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(admin.getUserId(), AccountType.CMA);

        doReturn(payTransactionLog).when(transactionLogRepository).save(any(TransactionLog.class));

        challengeService.payForChallenge(user.getUserId(), challenge.getChallengeId());

        verify(transactionLogRepository, times(1)).save(any(TransactionLog.class));
        verify(challengeUserRepository, times(1)).save(any(ChallengeUser.class));


    }


}

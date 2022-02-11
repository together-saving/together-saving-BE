package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.*;
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
import static com.savle.togethersaving.service.servicefixture.ChallengeUserFixture.challengeUser;
import static com.savle.togethersaving.service.servicefixture.ChallengeUserFixture.createChallengeUser;
import static com.savle.togethersaving.service.servicefixture.DtoFixture.createSavingDto;
import static com.savle.togethersaving.service.servicefixture.TransactionLogFixture.createTransactionLog;
import static com.savle.togethersaving.service.servicefixture.TransactionLogFixture.saveTransactionLog;
import static com.savle.togethersaving.service.servicefixture.UserFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    protected AccountRepository accountRepository;
    @Mock
    protected ChallengeRepository challengeRepository;
    @Mock
    private TransactionLogRepository transactionLogRepository;
    @Mock
    private TagService tagService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ChallengeUserRepository challengeUserRepository;


    @InjectMocks
    private UserService userService;


    @DisplayName("saveMoney -> 저축 성공")
    @Test
    void shouldSavingSuccessfully() {

        createUser();
        createAdmin();
        createAdminAccount();
        createChallenge();
        createTwoKindsOfUserAccounts();
        createSavingDto();
        createTransactionLog();
        createChallengeUser();

        doReturn(user)
                .when(userRepository).getUserByUserId(user.getUserId());
        doReturn(sendAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(user.getUserId(), AccountType.PHYSICAL);
        doReturn(challenge)
                .when(challengeRepository).getByChallengeId(challenge.getChallengeId());
        doReturn(receiveAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(user.getUserId(), AccountType.CMA);
        doReturn(saveTransactionLog)
                .when(transactionLogRepository).save(any(TransactionLog.class));
        doReturn(challengeUser)
                .when(challengeUserRepository).findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId
                        (any(Long.class),any(Long.class));

        userService.saveMoney(user.getUserId(), challenge.getChallengeId(), createSavingDto);

        verify(transactionLogRepository, times(1)).save(any(TransactionLog.class));

    }


}

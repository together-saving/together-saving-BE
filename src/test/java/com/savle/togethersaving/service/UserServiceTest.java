package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.ChallengeUserPK;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UserServiceTest extends ServiceTestUtil {

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

        createUserAndChallenge();
        createTwoKindsOfUserAccountsAndAdminAccount();
        createDtos();
        createTransactionLog();
        createChallengeUser();

        doReturn(user)
                .when(userRepository).getUserByUserId(user.getUserId());
        doReturn(sendAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(user.getUserId(), AccountType.PHYSICAL);
        doReturn(challenge)
                .when(challengeRepository).getById(challenge.getChallengeId());
        doReturn(receiveAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(user.getUserId(), AccountType.CMA);
        doReturn(saveTransactionLog)
                .when(transactionLogRepository).save(any(TransactionLog.class));
        doReturn(challengeUser)
                .when(challengeUserRepository).getById(any(ChallengeUserPK.class));

        userService.saveMoney(user.getUserId(), challenge.getChallengeId(), createSavingDto);

        verify(transactionLogRepository, times(1)).save(any(TransactionLog.class));

    }


}

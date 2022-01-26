package com.savle.togethersaving.service;

import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ReviewRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

}

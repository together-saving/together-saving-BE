package com.savle.togethersaving.service;

import com.savle.togethersaving.repository.AccountRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.repository.TransactionLogRepository;
import com.savle.togethersaving.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class SavingServiceTest /*extends ServiceTestUtil*/ {

    /*    @Mock
        private AccountRepository accountRepository;
        @Mock
        private TransactionLogRepository transactionLogRepository;
        @Mock
        protected AccountService accountService;
        @Mock
        private ChallengeUserRepository challengeUserRepository;
        @Mock
        private UserRepository userRepository;
        @InjectMocks

     */

    @Autowired
    private SavingService savingService;

    @DisplayName("내 저축 내역 보기")
    @Test
    void getMySavingHistory() {
        //given
//        createUserAndChallenge();
//        createDtos();
//        createTwoKindsOfAccounts();
        savingService.getSavingStatus(1L,1L);

        System.out.println(        savingService.getSavingStatus(1L,1L));

    }

}
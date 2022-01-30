package com.savle.togethersaving.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        savingService.getSavingStatus(1L, 1L);

        System.out.println(savingService.getSavingStatus(1L, 1L));

    }

}
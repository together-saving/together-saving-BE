package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.AccountRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.repository.TransactionLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SavingServiceTest extends ServiceTestUtil {

    @Mock
    protected TransactionLogRepository transactionLogRepository;
    @Mock
    protected AccountRepository accountRepository;
    @Mock
    protected ChallengeUserRepository challengeUserRepository;
    @InjectMocks
    private SavingService savingService;

    @DisplayName("내 저축 내역 보기")
    @Test
    void getMySavingHistory() {
        //given
        createUserAndChallenge();
        createTwoKindsOfUserAccountsAndAdminAccount();
        createDtos();
        createTransactionLog();
        createChallengeUser();
        PageRequest pageRequest = PageRequest.of(0,1000, Sort.by("created_at").descending());
        //when
        doReturn(sendAccount)
                .when(accountRepository).findAccountByOwner_UserIdAndAccountType(
                        user.getUserId(), AccountType.PHYSICAL);
        doReturn(challengeUser)
                .when(challengeUserRepository).
                findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(user.getUserId(),challenge.getChallengeId());

        List<TransactionLog> transactionLogs = new ArrayList<>();
        saveTransactionLog.setCreatedAt(LocalDateTime.now());
        transactionLogs.add(saveTransactionLog);
        doReturn(transactionLogs)
                .when(transactionLogRepository).
                getSavingHistorys(user.getUserId(), challenge.getChallengeId(), 0, pageRequest);

        SavingStatusDto savingStatusDto =  savingService.getSavingStatus(
                user.getUserId(), challenge.getChallengeId(), "today", pageRequest);
        //then
        assertEquals(savingStatusDto.getBalance(),10000L);
        assertEquals(savingStatusDto.getBankName(),"kakao");
        assertEquals(savingStatusDto.getSavingHistory().size(),1);
    }

}
package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.saving.SavingDetailDto;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.*;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.ArgumentMatchers.any;
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
    @Mock
    protected UserRepository userRepository;
    @Mock
    protected ChallengeCountRepository challengeCountRepository;
    @Mock
    protected ChallengeRepository challengeRepository;

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
                findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challenge.getChallengeId(),user.getUserId());

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

    @DisplayName("내 저축 상세")
    @Test
    void getSavingDetail() {
        //given
        createUserAndChallenge();
        createTwoKindsOfUserAccountsAndAdminAccount();
        createDtos();
        createTransactionLog();
        createChallengeUser();
        createChallengeCount();

        doReturn(user).when(userRepository)
                .getUserByUserId(user.getUserId());

        doReturn(challenge).when(challengeRepository)
                .getByChallengeId(challenge.getChallengeId());

        doReturn(challengeUser).when(challengeUserRepository)
                .findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challenge.getChallengeId(),user.getUserId());

        doReturn(2).when(transactionLogRepository).getSuccessCount(user.getUserId(), challenge.getChallengeId());
        doReturn(challengeCount).when(challengeCountRepository)
                .getChallengeCountByChallengeId(challenge.getChallengeId());
        //when
        SavingDetailDto savingDetailDto = savingService.getSavingDetail(user.getUserId(),challenge.getChallengeId());
        //then

        Assertions.assertEquals(savingDetailDto.getAccumualtedAmount(),5000);
        Assertions.assertEquals(savingDetailDto.getSavingRate(),8);
        Assertions.assertEquals(savingDetailDto.getFailureCount(),5);
    }

}
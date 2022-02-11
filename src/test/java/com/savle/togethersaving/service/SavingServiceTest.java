//package com.savle.togethersaving.service;
//
//import com.savle.togethersaving.dto.saving.SavingDetailDto;
//import com.savle.togethersaving.dto.saving.SavingStatusDto;
//import com.savle.togethersaving.entity.AccountType;
//import com.savle.togethersaving.entity.TransactionLog;
//import com.savle.togethersaving.repository.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.savle.togethersaving.service.servicefixture.AccountFixture.createTwoKindsOfUserAccounts;
//import static com.savle.togethersaving.service.servicefixture.AccountFixture.sendAccount;
//import static com.savle.togethersaving.service.servicefixture.CountFixture.challengeCount;
//import static com.savle.togethersaving.service.servicefixture.CountFixture.createChallengeCount;
//import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.challenge;
//import static com.savle.togethersaving.service.servicefixture.ChallengeUserFixture.challengeUser;
//import static com.savle.togethersaving.service.servicefixture.ChallengeUserFixture.createChallengeUser;
//import static com.savle.togethersaving.service.servicefixture.DtoFixture.createSavingDto;
//import static com.savle.togethersaving.service.servicefixture.TransactionLogFixture.createTransactionLog;
//import static com.savle.togethersaving.service.servicefixture.TransactionLogFixture.saveTransactionLog;
//import static com.savle.togethersaving.service.servicefixture.UserFixture.createUser;
//import static com.savle.togethersaving.service.servicefixture.UserFixture.user;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doReturn;
//
//
//@ExtendWith(MockitoExtension.class)
//class SavingServiceTest {
//
//    @Mock
//    protected TransactionLogRepository transactionLogRepository;
//    @Mock
//    protected AccountRepository accountRepository;
//    @Mock
//    protected ChallengeUserRepository challengeUserRepository;
//    @Mock
//    protected UserRepository userRepository;
//    @Mock
//    protected ChallengeCountRepository challengeCountRepository;
//    @Mock
//    protected ChallengeRepository challengeRepository;
//
//    @InjectMocks
//    private SavingService savingService;
//
//    @DisplayName("내 저축 내역 보기")
//    @Test
//    void getMySavingHistory() {
//        //given
//        createUser();
//        createTwoKindsOfUserAccounts();
//        createSavingDto();
//        createTransactionLog();
//        createChallengeUser();
//
//        PageRequest pageRequest = PageRequest.of(0, 1000, Sort.by("created_at").descending());
//        //when
//        doReturn(sendAccount)
//                .when(userRepository).getUserByRole(
//                        user.getUserId(), AccountType.PHYSICAL);
//        doReturn(challengeUser)
//                .when(challengeUserRepository).
//                findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challenge.getChallengeId(), user.getUserId());
//
//        List<TransactionLog> transactionLogs = new ArrayList<>();
//        saveTransactionLog.setCreatedAt(LocalDateTime.now());
//        transactionLogs.add(saveTransactionLog);
//        doReturn(transactionLogs)
//                .when(transactionLogRepository).
//                getSavingHistories(user.getUserId(), challenge.getChallengeId(), 0, pageRequest);
//
//        SavingStatusDto savingStatusDto = savingService.getSavingStatus(
//                user.getUserId(), challenge.getChallengeId(), "today", );
//        //then
//        assertEquals(savingStatusDto.getBalance(), 10000L);
//        assertEquals(savingStatusDto.getBankName(), "kakao");
//        assertEquals(savingStatusDto.getSavingHistory().size(), 1);
//    }
//
//    @DisplayName("내 저축 상세")
//    @Test
//    void getSavingDetail() {
//        //given
//        createUser();
//        createTwoKindsOfUserAccounts();
//        createSavingDto();
//        createTransactionLog();
//        createChallengeUser();
//        createChallengeCount();
//
//        doReturn(user).when(userRepository)
//                .getUserByUserId(user.getUserId());
//
//        doReturn(challenge).when(challengeRepository)
//                .getByChallengeId(challenge.getChallengeId());
//
//        doReturn(challengeUser).when(challengeUserRepository)
//                .findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challenge.getChallengeId(), user.getUserId());
//
//        doReturn(2).when(transactionLogRepository).getSuccessCount(user.getUserId(), challenge.getChallengeId());
//        doReturn(challengeCount).when(challengeCountRepository)
//                .getChallengeCountByChallenge_ChallengeId(challenge.getChallengeId());
//        //when
//        SavingDetailDto savingDetailDto = savingService.getSavingDetail(user.getUserId(), challenge.getChallengeId());
//        //then
//
//        Assertions.assertEquals(savingDetailDto.getAccumualtedAmount(), 5000);
//        Assertions.assertEquals(savingDetailDto.getSavingRate(), 8);
//        Assertions.assertEquals(savingDetailDto.getFailureCount(), 5);
//    }
//
//}
package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.challenge.ChallengeDetailDto;
import com.savle.togethersaving.dto.challenge.PopularChallengeDto;
import com.savle.togethersaving.dto.review.ChallengeReviewDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final TagService tagService;
    private final WishService wishService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final ChallengeUserRepository challengeUserRepository;
    private final ReviewService reviewService;
    private final ChallengeFrequencyRepository frequencyRepository;


    @Transactional
    public void changeAutoSettings(Long userId, Long challengeId){
        ChallengeUser challengeUser = challengeUserRepository
                .findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challengeId,userId);

        if(!challengeUser.getIsAutomated()){
            challengeUser.setIsAutomated(true);
        }else{
            challengeUser.setIsAutomated(false);
        }
    }

    public List<PopularChallengeDto> getChallenges(Long userId, Pageable pageable) {
        List<Challenge> challengeList = challengeRepository
                .findChallengesByStartDateGreaterThan(LocalDate.now(), pageable);
        return challengeList.stream()
                .map(challenge -> mapToPopularDto(challenge, userId))
                .collect(Collectors.toList());
    }

    private PopularChallengeDto mapToPopularDto(Challenge challenge, Long userId) {
        PopularChallengeDto dto = PopularChallengeDto.challengeOf(challenge);
        dto.setTags(tagService.tagsOf(challenge));
        if(userId == -1L) {
            dto.setWished(false);
        }else {
            dto.setWished(wishService.isWished(challenge, userId));
        }
        return dto;
    }

    @Transactional
    public void payForChallenge(Long userId, Long challengeId) {
        User user = userRepository.getUserByUserId(userId);
        //중앙 cma 계좌 조회
        User admin = userRepository.getUserByRole("ADMIN");

        Challenge challenge = getChallengeByChallengeId(challengeId);
        Long entryFee = challenge.getEntryFee();
        // 유저id로 physical 계좌를 찾기
        Account sendAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);

        Account receiveAccount = null;
        // physical 계좌에 결제금 보다 돈이 많은지 검사.
        if (sendAccount.getBalance() - entryFee >= 0) {

            // 받을 계좌 조회
            receiveAccount = accountRepository.findAccountByOwner_UserIdAndAccountType(admin.getUserId(), AccountType.CMA);

            sendAccount.withdraw(entryFee);
            receiveAccount.deposit(entryFee);
            //거래 내역 저장
            TransactionLog transactionLog = TransactionLog.builder()
                    .challenge(challenge)
                    .amount(entryFee)
                    .sendAccount(sendAccount)
                    .receiveAccount(receiveAccount)
                    .build();

            TransactionLog savedTransactionLog =transactionLogRepository.save(transactionLog);

            savedTransactionLog.addSendAccountLog(sendAccount);
            savedTransactionLog.addReceiveAccountLog(receiveAccount);
            savedTransactionLog.addChallengeLog(challenge);

            ChallengeUser challengeUser = ChallengeUser.builder()
                    .challenge(challenge)
                    .user(user)
                    .build();

            challengeUserRepository.save(challengeUser);

            challenge.addMember();
        }
    }

    public Challenge getChallengeByChallengeId(Long challengeId) {

        return challengeRepository.getById(challengeId);
    }

    public ChallengeDetailDto getChallengeDetail(Long challengeId, Long userId) {
        return mapToDetailDto(challengeRepository.getById(challengeId), userId);
    }

    private ChallengeDetailDto mapToDetailDto(Challenge challenge, Long userId) {
        ChallengeDetailDto dto = ChallengeDetailDto.challengeOf(challenge);
        dto.setTags(tagService.tagsOf(challenge));
        dto.setWished(wishService.isWished(challenge, userId));
        dto.setParticipated(challengeUserRepository.existsByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(challenge.getChallengeId(),userId));
        dto.setChallengeReviews(reviewService.reviewDtoOf(challenge.getChallengeId()));
        dto.setChallengeFrequency(frequencyRepository.findAllByChallengeFrequencyPK_ChallengeId(challenge.getChallengeId())
                .stream()
                .map(challengeFrequency -> challengeFrequency.getChallengeFrequencyPK().getFrequency())
                .collect(Collectors.toList())
        );
        return dto;
    }
}
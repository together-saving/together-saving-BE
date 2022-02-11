package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.ChallengeCount;
import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.repository.ChallengeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChallengeCountService {
    private final ChallengeCountRepository challengeCountRepository;

    @Transactional
    public void caculateCount(ChallengeFrequency challengeFrequency) {
        ChallengeCount cc = challengeCountRepository.getChallengeCountByChallenge_ChallengeId(challengeFrequency.getChallenge().getChallengeId());
        cc.setCurrentCount(cc.getCurrentCount() + 1);
        cc.setRemainCount(cc.getMaxCount() - cc.getCurrentCount());
        challengeCountRepository.save(cc);
    }
}

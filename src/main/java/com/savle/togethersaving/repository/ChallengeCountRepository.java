package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeCountRepository extends JpaRepository<ChallengeCount,Long> {
    ChallengeCount getChallengeCountByChallenge_ChallengeId(Long challengeId);
}

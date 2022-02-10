package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Count;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeCountRepository extends JpaRepository<Count,Long> {
    Count getChallengeCountByChallengeId(Long challengeId);
}

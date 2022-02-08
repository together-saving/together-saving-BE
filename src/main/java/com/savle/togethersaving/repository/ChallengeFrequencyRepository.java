package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.ChallengeFrequencyPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeFrequencyRepository extends JpaRepository<ChallengeFrequency, ChallengeFrequencyPK> {
	List<ChallengeFrequency> findAllByChallengeFrequencyPK_ChallengeId(Long challengeId);
}

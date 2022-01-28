package com.savle.togethersaving.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.ChallengeFrequencyPK;
import com.savle.togethersaving.entity.Frequency;

public interface ChallengeFrequencyRepository extends JpaRepository<ChallengeFrequency, ChallengeFrequencyPK> {
	List<Frequency> findAllByChallengeFrequencyPK_ChallengeId(Long challengeId);
}

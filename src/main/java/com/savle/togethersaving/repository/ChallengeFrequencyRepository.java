package com.savle.togethersaving.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.ChallengeFrequencyPK;
import com.savle.togethersaving.entity.Frequency;
import org.springframework.data.jpa.repository.Query;

public interface ChallengeFrequencyRepository extends JpaRepository<ChallengeFrequency, ChallengeFrequencyPK> {
	List<ChallengeFrequency> findAllByChallengeFrequencyPK_ChallengeId(Long challengeId);

}

package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.ChallengeFrequencyPK;

import com.savle.togethersaving.entity.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;


public interface ChallengeFrequencyRepository extends JpaRepository<ChallengeFrequency, ChallengeFrequencyPK> {
	List<ChallengeFrequency> findAllByChallengeFrequencyPK_ChallengeId(Long challengeId);

	@Query("select cf from ChallengeFrequency cf join fetch cf.challenge where cf.challengeFrequencyPK.frequency=:day ")
	List<ChallengeFrequency> findChallengeFrequencyListByDay(Frequency day);
}

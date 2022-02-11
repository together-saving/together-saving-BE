package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;

import java.time.LocalDate;
import java.util.List;

import com.savle.togethersaving.entity.ChallengeFrequency;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate);

	List<Challenge> findByEndDateIs(LocalDate endDate);
	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate, Pageable pageable);
	Challenge getByChallengeId(Long challengeId);

	@Query(value = "update challenge set is_active = true  where is_active = false and start_date = curdate()" , nativeQuery = true )
	void openChallenge();
	@Query("select c from Challenge c join fetch c.days where c.challengeId = ?1")
	Challenge findChallengeByChallengeId(Long challengeId);

	List<Challenge> findChallengesByEndDate(LocalDate now);
}

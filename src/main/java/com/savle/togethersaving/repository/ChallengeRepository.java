package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;

import java.time.LocalDate;
import java.util.List;

import com.savle.togethersaving.entity.ChallengeFrequency;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate);
	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate, Pageable pageable);
	Challenge getByChallengeId(Long challengeId);
}

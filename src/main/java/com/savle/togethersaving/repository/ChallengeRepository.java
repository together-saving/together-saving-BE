package com.savle.togethersaving.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savle.togethersaving.entity.Challenge;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate);
}

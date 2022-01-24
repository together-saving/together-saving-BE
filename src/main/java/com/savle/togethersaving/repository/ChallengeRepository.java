package com.savle.togethersaving.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.savle.togethersaving.entity.Challenge;

@Repository
public interface ChallengeRepository extends PagingAndSortingRepository<Challenge, Long> {

	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate);
	List<Challenge> findChallengesByStartDateGreaterThan(LocalDate localDate, Sort sort);
}

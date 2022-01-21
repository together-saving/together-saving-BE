package com.savle.togethersaving.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.repository.ChallengeRepository;

@Service
public class ChallengeService {

	private final ChallengeRepository challengeRepository;

	@Autowired
	public ChallengeService(ChallengeRepository challengeRepository) {
		this.challengeRepository = challengeRepository;
	}

	public List<Challenge> getPopularChallenges() {
		return challengeRepository
			.findChallengesByStartDateGreaterThan(LocalDate.now(), Sort.by("members").descending());
	}
}

package com.savle.togethersaving.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;

@SpringBootTest
class ChallengeRepositoryTest {

	private final ChallengeRepository challengeRepository;

	@Autowired
	ChallengeRepositoryTest(ChallengeRepository challengeRepository) {
		this.challengeRepository = challengeRepository;
	}

	@Test
	void findPopularChallenge() {
		Challenge chal1 = Challenge.builder().content("previous").entryFee(0L).members(1L)
			.mode(Mode.FREE).payment(0L).startDate(LocalDate.now().minusDays(1L))
			.period(1).build();
		Challenge chal2 = Challenge.builder().content("post").entryFee(0L).members(1L)
			.mode(Mode.FREE).payment(0L).startDate(LocalDate.now().plusDays(1L))
			.period(1).build();
		challengeRepository.save(chal1);
		challengeRepository.save(chal2);
		List<Challenge> challengesByStartDateAfter
			= challengeRepository.findChallengesByStartDateGreaterThan(LocalDate.now());
		Assertions.assertThat(challengesByStartDateAfter.size()).isEqualTo(1);
	}

}
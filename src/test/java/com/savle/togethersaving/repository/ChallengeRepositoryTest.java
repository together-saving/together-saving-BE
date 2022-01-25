package com.savle.togethersaving.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import com.savle.togethersaving.entity.Challenge;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChallengeRepositoryTest {

	@Autowired
	private ChallengeRepository challengeRepository;

	@Test
	void beforeStartChallengeTest() {
		//given
		Challenge previous = Challenge.builder()
			.title("1")
			.startDate(LocalDate.now().plusDays(1)).build();
		Challenge after = Challenge.builder()
			.title("2")
			.startDate(LocalDate.now().minusDays(1)).build();
		challengeRepository.save(previous);
		challengeRepository.save(after);

		//when
		List<Challenge> challengesByStartDateAfter
			= challengeRepository.findChallengesByStartDateGreaterThan(LocalDate.now());

		//then
		Assertions.assertThat(challengesByStartDateAfter.size()).isEqualTo(1);
	}

	@Test
	void popularChallengeTest() {
		//given
		Challenge previous = Challenge.builder()
			.title("1")
			.members(1L)
			.startDate(LocalDate.now().plusDays(1)).build();
		Challenge after = Challenge.builder()
			.title("2")
			.members(2L)
			.startDate(LocalDate.now().minusDays(1)).build();
		Challenge biggest = Challenge.builder()
			.title("3")
			.members(15L)
			.startDate(LocalDate.now().plusDays(1)).build();
		challengeRepository.save(previous);
		challengeRepository.save(after);
		challengeRepository.save(biggest);

		//when
		List<Challenge> popularChallenges
			= challengeRepository
				.findChallengesByStartDateGreaterThan(LocalDate.now(), Sort.by("members").descending());
		//then
		Assertions.assertThat(popularChallenges.get(0).getMembers()).isEqualTo(15L);
	}
}
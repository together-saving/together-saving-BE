package com.savle.togethersaving.repository;

import java.time.LocalDate;
import java.util.List;

import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Role;
import com.savle.togethersaving.entity.User;
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

	@Autowired
	private  UserRepository userRepository;

	@Test
	void beforeStartChallengeTest() {
		//given
		User user = User.builder()
				.email("sheep@naver.com")
				.birth(LocalDate.of(2020, 01, 01))
				.gender(true)
				.password("1234")
				.phoneNumber("010-1234-5678")
				.profilePicture("http://asdasd.com")
				.nickname("NICK")
				.role(Role.USER)
				.point(0L)
				.reward(0L)
				.build();
		userRepository.save(user);
		Challenge previous = Challenge.builder()
				.host(user)
				.startDate(LocalDate.now().plusDays(2L))
				.title("돈 모으자")
				.content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
				.payment(5000L)
				.members(100L)
				.mode(Mode.FREE)
				.entryFee(5000L)
				.period(3)
				.thumbnail("http://qweqweqwe.com").build();

		Challenge after = Challenge.builder()
				.host(user)
				.startDate(LocalDate.now().minusDays(2L))
				.title("돈 모으자")
				.content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지였다")
				.payment(15000L)
				.members(100L)
				.mode(Mode.FREE)
				.entryFee(5000L)
				.period(3)
				.thumbnail("http://qweqweqwe.com").build();
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
		User user = User.builder()
				.email("sheep@naver.com")
				.birth(LocalDate.of(2020, 01, 01))
				.gender(true)
				.password("1234")
				.phoneNumber("010-1234-5678")
				.profilePicture("http://asdasd.com")
				.nickname("NICK")
				.role(Role.USER)
				.point(0L)
				.reward(0L)
				.build();

		userRepository.save(user);

		Challenge previous = Challenge.builder()
				.host(user)
				.startDate(LocalDate.now().plusDays(2L))
				.title("돈 모으자")
				.content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
				.payment(5000L)
				.members(14L)
				.mode(Mode.FREE)
				.entryFee(5000L)
				.period(3)
				.thumbnail("http://qweqweqwe.com").build();
		Challenge after = Challenge.builder()
				.host(user)
				.startDate(LocalDate.now().plusDays(2L))
				.title("돈 모으자")
				.content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
				.payment(5000L)
				.members(13L)
				.mode(Mode.FREE)
				.entryFee(5000L)
				.period(3)
				.thumbnail("http://qweqweqwe.com").build();
		Challenge biggest = Challenge.builder()
				.host(user)
				.startDate(LocalDate.now().plusDays(2L))
				.title("돈 모으자")
				.content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
				.payment(5000L)
				.members(15L)
				.mode(Mode.FREE)
				.entryFee(5000L)
				.period(3)
				.thumbnail("http://qweqweqwe.com").build();
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
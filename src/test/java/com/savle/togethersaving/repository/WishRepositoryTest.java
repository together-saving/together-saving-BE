package com.savle.togethersaving.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import com.savle.togethersaving.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.savle.togethersaving.service.WishService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WishRepositoryTest {

	@Autowired
	private WishRepository wishRepository;

	@Autowired
	private ChallengeRepository challengeRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void existsByHopingPerson_UserIdAndChallenge() {
		//when

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
				.thumbnail("http://qweqweqwe.com")
				.build();
		challengeRepository.save(previous);

		Wish wish = Wish.builder().challenge(previous).hopingPerson(user).build();
		wishRepository.save(wish);
		Wish wish1 = wishRepository.findById(wish.getWishId()).get();
		Assertions.assertThat(wish.getChallenge()).isEqualTo(wish1.getChallenge());
		Assertions.assertThat(wish.getHopingPerson()).isEqualTo(wish1.getHopingPerson());
		Assertions.assertThat(wishRepository.existsByHopingPerson_UserIdAndChallenge(user.getUserId(), previous)).isEqualTo(true);
	}
}
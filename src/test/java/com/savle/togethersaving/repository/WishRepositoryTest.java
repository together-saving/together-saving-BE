package com.savle.togethersaving.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;
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
		Challenge previous = Challenge.builder()
			.title("1")
			.startDate(LocalDate.now().plusDays(1)).build();
		User user = User.builder().email("1").birth(LocalDate.now()).gender(true).password("1234").phoneNumber("123").build();
		userRepository.save(user);
		challengeRepository.save(previous);

		Wish wish = Wish.builder().challenge(previous).hopingPerson(user).build();
		wishRepository.save(wish);
		Wish wish1 = wishRepository.findById(wish.getWishId()).get();
		Assertions.assertThat(wish.getChallenge()).isEqualTo(wish1.getChallenge());
		Assertions.assertThat(wish.getHopingPerson()).isEqualTo(wish1.getHopingPerson());
		Assertions.assertThat(wishRepository.existsByHopingPerson_UserIdAndChallenge(user.getUserId(), previous)).isEqualTo(true);
	}
}
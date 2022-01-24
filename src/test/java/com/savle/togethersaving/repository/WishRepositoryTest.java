package com.savle.togethersaving.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.service.WishService;

@DataJpaTest
class WishRepositoryTest {

	@Autowired
	private WishRepository wishRepository;

	@Test
	void existsByHopingPerson_UserIdAndChallenge() {
		//when
		Challenge previous = Challenge.builder().challengeId(1L)
			.title("1")
			.startDate(LocalDate.now().plusDays(1)).build();
		User user = User.builder().userId(1L).email("1").birth(LocalDate.now()).gender(true).phoneNumber("123").build();
		Wish wish = Wish.builder().challenge(previous).hopingPerson(user).build();
		wishRepository.save(wish);
		Wish wish1 = wishRepository.findById(1L).get();
		Assertions.assertThat(wish.getChallenge()).isEqualTo(wish1.getChallenge());
		Assertions.assertThat(wish.getHopingPerson()).isEqualTo(wish1.getHopingPerson());
	}
}
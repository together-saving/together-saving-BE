package com.savle.togethersaving.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Role;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;

@ExtendWith(MockitoExtension.class)
class WishServiceTest {

	@InjectMocks
	private WishService wishService;

	@Mock
	private ChallengeRepository challengeRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private WishRepository wishRepository;

	@Test
	void addWish() {
		//given
		Challenge challenge = Challenge.builder()
			.challengeId(1L)
			.title("테스트 챌린지")
			.startDate(LocalDate.now()).build();
		User user = User.builder()
			.email("sheep@naver.com")
			.birth(LocalDate.of(2020, 01, 01))
			.gender(true)
			.phoneNumber("010-1234-5678")
			.profilePicture("http://asdasd.com")
			.nickname("NICK")
			.role(Role.USER)
			.point(0L)
			.reward(0L)
			.build();

		given(wishRepository.existsByHopingPerson_UserIdAndChallenge(user.getUserId(), challenge)).willReturn(true);

		//when
		wishService.addWish(user.getUserId(), challenge.getChallengeId());
		//then
		Assertions.assertThat(wishService.isWished(challenge, user.getUserId())).isEqualTo(true);
	}
}
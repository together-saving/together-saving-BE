package com.savle.togethersaving.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ChallengeTagRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;

@AutoConfigureMockMvc
@SpringBootTest
class ChallengeControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private ChallengeRepository challengeRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private WishRepository wishRepository;

	@MockBean
	private ChallengeTagRepository challengeTagRepository;

	@Autowired
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void getPopularChallengesTestV1() throws Exception {
		//given
		User user = User.builder().userId(1L).email("1").birth(LocalDate.now()).gender(true).phoneNumber("123").build();
		Challenge smallest = Challenge.builder().challengeId(1L)
			.title("smallest")
			.members(1L)
			.startDate(LocalDate.now().plusDays(1)).build();
		Challenge outdated = Challenge.builder().challengeId(2L)
			.title("outdated")
			.members(2L)
			.startDate(LocalDate.now().minusDays(1)).build();
		Challenge biggest = Challenge.builder().challengeId(3L)
			.title("biggest")
			.members(15L)
			.startDate(LocalDate.now().plusDays(1)).build();
		List<Challenge> popularList = Arrays.asList(biggest, smallest);
		given(challengeRepository
				.findChallengesByStartDateGreaterThan(LocalDate.now(), Sort.by("members").descending()))
			.willReturn(popularList);
		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		given(wishRepository.existsByHopingPerson_UserIdAndChallenge(user.getUserId(), biggest))
			.willReturn(true);
		given(challengeTagRepository.findChallengeTagsByChallenge(biggest))
			.willReturn(Arrays.asList(ChallengeTag.builder().challenge(biggest)
															.tag(Tag.builder().name("tagTest").build())
				.build()));

		//when
		ResultActions result = mockMvc.perform(
			get("/api/v1/auth/challenges?criteria=popularity")
				.header("user-id", 1L)
				.accept(MediaType.APPLICATION_JSON)
		);

		//then
		result.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.data[0].title", is("biggest")))
			.andExpect(jsonPath("$.data[1].title", is("smallest")))
		;
	}
}
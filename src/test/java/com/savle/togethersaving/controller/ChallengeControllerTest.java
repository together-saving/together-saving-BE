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
import com.savle.togethersaving.repository.ChallengeRepository;

@AutoConfigureMockMvc
@SpringBootTest
class ChallengeControllerTest {
	private MockMvc mockMvc;

	@MockBean
	private ChallengeRepository challengeRepository;

	@Autowired
	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void getPopularChallengesTestV1() throws Exception {
		//given
		Challenge smallest = Challenge.builder()
			.title("smallest")
			.members(1L)
			.startDate(LocalDate.now().plusDays(1)).build();
		Challenge outdated = Challenge.builder()
			.title("outdated")
			.members(2L)
			.startDate(LocalDate.now().minusDays(1)).build();
		Challenge biggest = Challenge.builder()
			.title("biggest")
			.members(15L)
			.startDate(LocalDate.now().plusDays(1)).build();
		List<Challenge> popularList = Arrays.asList(biggest, smallest);
		given(challengeRepository
				.findChallengesByStartDateGreaterThan(LocalDate.now(), Sort.by("members").descending()))
			.willReturn(popularList);

		//when
		ResultActions result = mockMvc.perform(
			get("/api/v1/auth/challenges?criteria=popularity")
				.accept(MediaType.APPLICATION_JSON)
		);

		//then
		result.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data").isArray())
			.andExpect(jsonPath("$.data[0].title", is("biggest")))
			.andExpect(jsonPath("$.data[0].members", is(15)))
			.andExpect(jsonPath("$.data[1].title", is("smallest")))
			.andExpect(jsonPath("$.data[1].members", is(1)))
		;
	}
}
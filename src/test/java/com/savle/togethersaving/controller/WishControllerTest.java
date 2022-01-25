package com.savle.togethersaving.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Role;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.UserService;
import com.savle.togethersaving.service.WishService;

@SpringBootTest
@AutoConfigureMockMvc
class WishControllerTest extends ControllerTestUtil{

	@MockBean
	private WishService wishService;

	@Test
	void addWish() throws Exception {
		createUserAndChallenge();

		//given
		given(wishService.addWish(1L, 1L))
			.willReturn(Wish.builder().wishId(1L).hopingPerson(user).challenge(previousChallenge).build());

		//when
		ResultActions result = mockMvc.perform(
			post("/api/v1/users/challenges/1/wish")
				.header("user-id", 1L)
				.accept(MediaType.APPLICATION_JSON)
		);

		//then
		result.andDo(print())
			.andExpect(status().isOk())
		;
	}
}
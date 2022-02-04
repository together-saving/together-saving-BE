package com.savle.togethersaving.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.savle.togethersaving.config.security.JwtProperties;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishControllerTest extends ControllerTestUtil {

    @MockBean
    private WishService wishService;
    @MockBean
    UserRepository userRepository;


    @Test
    void addWish() throws Exception {
        createUserAndChallenge();
        createJwtToken();
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        given(wishService.addWish(1L, 1L))
                .willReturn(Wish.builder().wishId(1L).hopingPerson(user).challenge(challenge1).build());
        //when
        ResultActions result = mockMvc.perform(
                post("/api/v1/users/challenges/1/wish")
                        .header("user-id", 1L)
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
                        .accept(MediaType.APPLICATION_JSON)
        );
        //then
        result.andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    void deleteWish() throws Exception {
        createUserAndChallenge();

        createJwtToken();

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        ResultActions result = mockMvc.perform(
                delete("/api/v1/users/challenges/1/wish")
                        .header("user-id", 1L)
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
                .andExpect(status().isOk())
        ;
    }
}
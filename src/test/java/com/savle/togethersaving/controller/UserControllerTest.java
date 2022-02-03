package com.savle.togethersaving.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.savle.togethersaving.config.security.JwtProperties;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.entity.Tag;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("User Controller test")
public class UserControllerTest extends ControllerTestUtil {


    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;


    @Test
    @DisplayName("저축하기 성공")
    void savingApiTest() throws Exception {

        createUserAndChallenge();

        CreateSavingsDto createSavingDto = CreateSavingsDto.builder()
                .savingAmount(5000L)
                .build();

        Algorithm AL = Algorithm.HMAC512(JwtProperties.SECRET);

        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", user.getUserId())
                .withClaim("email", user.getEmail())
                .sign(AL);

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        String content = objectMapper.writeValueAsString(createSavingDto);

        mockMvc.perform(post("/api/v1/challenges/1/saving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
                        .content(content))
                .andExpect(status().isOk());

        verify(userService).saveMoney(eq(1L), eq(1L), any());
    }

    @Test
    @DisplayName("참여중인 챌린지 조회")
    void retrieveMyChallenges() throws Exception {

        createUserAndChallenge();

        createJwtToken();
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        List<ResponseMyChallengeDto> myChallengeList = Arrays.asList(challenge3, challenge1).stream()
                .map(challenge -> {
                    ResponseMyChallengeDto dto = ResponseMyChallengeDto.toDto(challenge);
                    dto.setTags(Arrays.asList(Tag.builder().name("tag1").build(), Tag.builder().name("tag2").build()));
                    return dto;
                }).collect(Collectors.toList());
        given(userService.getMyParticipatingChallenges(1L, PageRequest.of(0, 7, Sort.by("challenge.members").descending())))
                .willReturn(myChallengeList);

        //when
        ResultActions result = mockMvc.perform(
                get("/api/v1/users/challenges?page=0")
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].title", is("돈 모으자3")))
                .andExpect(jsonPath("$.data[1].title", is("돈 모으자1")))
        ;
    }
}
package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.PopularChallengeDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ChallengeTagRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;
import com.savle.togethersaving.service.ChallengeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ChallengeControllerTest extends ControllerTestUtil {


    @MockBean
    private ChallengeService challengeService;


    @Test
    void getPopularChallengesTestV1() throws Exception {
        //given
        createUserAndChallenge();

        List<PopularChallengeDto> popularList = Arrays.asList(biggestChallenge, previousChallenge).stream()
                .map(challenge -> {
                    PopularChallengeDto dto = PopularChallengeDto.challengeOf(challenge);
                    dto.setTags(Arrays.asList(Tag.builder().name("tag1").build(), Tag.builder().name("tag2").build()));
                    dto.setWished(true);
                    return dto;
                }).collect(Collectors.toList());
        given(challengeService.getChallenges(1L, PageRequest.of(0, 7, Sort.by("members").descending())))
                .willReturn(popularList);

        //when
        ResultActions result = mockMvc.perform(
                get("/api/v1/auth/challenges?criteria=popularity&page=0")
                        .header("user-id", 1L)
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

    @Test
    void payChallengeTest() throws Exception{

        createUserAndChallenge();

       doNothing().when(challengeService).payForChallenge(any(Long.class),any(Long.class));

        ResultActions result = mockMvc.perform(
                post("/api/v1/challenges/1/payment")
                        .accept(MediaType.APPLICATION_JSON));


        result.andDo(print())
                .andExpect(status().isOk());
    }
}
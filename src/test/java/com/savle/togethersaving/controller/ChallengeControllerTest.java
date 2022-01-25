package com.savle.togethersaving.controller;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ChallengeTagRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ChallengeControllerTest extends ControllerTestUtil {

    @MockBean
    private ChallengeRepository challengeRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WishRepository wishRepository;

    @MockBean
    private ChallengeTagRepository challengeTagRepository;


    @Test
    void getPopularChallengesTestV1() throws Exception {
        //given
        createUserAndChallenge();
        createUserAndChallenge();

        List<Challenge> popularList = Arrays.asList(biggestChallenge, previousChallenge);
        given(challengeRepository
                .findChallengesByStartDateGreaterThan(LocalDate.now(), Sort.by("members").descending()))
                .willReturn(popularList);
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(wishRepository.existsByHopingPerson_UserIdAndChallenge(user.getUserId(), biggestChallenge))
                .willReturn(true);
        given(challengeTagRepository.findChallengeTagsByChallenge(biggestChallenge))
                .willReturn(Arrays.asList(ChallengeTag.builder().challenge(biggestChallenge)
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
                .andExpect(jsonPath("$.data[0].title", is("돈 모으자3")))
                .andExpect(jsonPath("$.data[1].title", is("돈 모으자1")))
        ;
    }
}
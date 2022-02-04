package com.savle.togethersaving.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.savle.togethersaving.config.security.JwtProperties;
import com.savle.togethersaving.dto.saving.SavingDetailDto;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.SavingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.ResultActions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SavingControllerTest extends ControllerTestUtil{

    @MockBean
    UserRepository userRepository;

    @MockBean
    SavingService savingService;

    @Test
    @DisplayName("내 저축 히스토리")
    void mySavingHistory() throws Exception {
        //given
        createUserAndChallenge();

        Algorithm AL = Algorithm.HMAC512(JwtProperties.SECRET);

        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", user.getUserId())
                .withClaim("email", user.getEmail())
                .sign(AL);

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        SavingStatusDto.History history1 = SavingStatusDto.History.builder()
                .date(LocalDate.now().minusDays(1))
                .dayOfWeek(DayOfWeek.MONDAY)
                .amount(10000L)
                .build();
        SavingStatusDto.History history2 = SavingStatusDto.History.builder()
                .date(LocalDate.now().minusDays(2))
                .dayOfWeek(DayOfWeek.SUNDAY)
                .amount(10000L)
                .build();
        SavingStatusDto savingStatusDto = SavingStatusDto.builder()
                .accountNumber("1111-2222")
                .bankName("test bank")
                .thumbnail("imgur/testimage.png")
                .balance(100000L)
                .isAutomated(false)
                .savingHistory(Arrays.asList(history1,history2)).build();


        PageRequest pageRequest = PageRequest.of(0,1000, Sort.by("created_at").descending());
        given(savingService.getSavingStatus(1L,1L,"1week", pageRequest))
                .willReturn(savingStatusDto);

        //when
        ResultActions result = mockMvc.perform(
                get("/api/v1/users/challenges/1/saving-histories").contentType(contentType)
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
                        .param("period" , "1week")
                        .param("ordering", "desc")
        );
        // then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.account_number", is("1111-2222")))
        ;
    }

    @Test
    @DisplayName("내 저축 상세")
    void savingDetail() throws Exception {
        //given
        createUserAndChallenge();

        Algorithm AL = Algorithm.HMAC512(JwtProperties.SECRET);

        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", user.getUserId())
                .withClaim("email", user.getEmail())
                .sign(AL);

        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        SavingDetailDto savingDetailDto = SavingDetailDto.builder()
                .savingRate(8)
                .accumualtedAmount(5000L)
                .failureCount(5)
                .remainCount(5)
                .successCount(5)
                .thumbnail(user.getProfilePicture())
                .nickname(user.getNickname())
                .build();

                given(savingService.getSavingDetail(user.getUserId(),challenge1.getChallengeId()))
                .willReturn(savingDetailDto);
        //when
        ResultActions result = mockMvc.perform(
                get("/api/v1/users/challenges/1/saving-detail").contentType(contentType)
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
        );
        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.success_count", is(5)));

    }
}

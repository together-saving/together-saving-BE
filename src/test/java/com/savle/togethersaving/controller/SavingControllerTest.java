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

//    @Test
//    @DisplayName("내 저축 히스토리")
//    void mySavingHistory() throws Exception {
//        //given
//        createUserAndChallenge();
//
//        Algorithm AL = Algorithm.HMAC512(JwtProperties.SECRET);
//
//        String jwtToken = JWT.create()
//                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
//                .withClaim("id", user.getUserId())
//                .withClaim("email", user.getEmail())
//                .sign(AL);
//
//        given(userRepository.findByEmail(user.getEmail()))
//                .willReturn(user);
//
//        SavingStatusDto.History history1 = new SavingStatusDto.History();
//                history1.setDate(LocalDate.now().minusDays(1));
//                history1.setDayOfWeek(DayOfWeek.MONDAY);
//                history1.setAmount(10000L);
//        SavingStatusDto.History history2 = new SavingStatusDto.History();
//                history2.setDate(LocalDate.now().minusDays(2));
//                history2.setDayOfWeek(DayOfWeek.SUNDAY);
//                history2.setAmount(10000L);
//        SavingStatusDto savingStatusDto = new SavingStatusDto();
//                savingStatusDto.setAccountNumber("1111-2222");
//                savingStatusDto.setBankName("test bank");
//                savingStatusDto.setThumbnail("imgur/testimage.png");
//                savingStatusDto.setBalance(100000L);
//                savingStatusDto.setIsAutomated(false);
//                savingStatusDto.setSavingHistory(Arrays.asList(history1,history2));
//
//
//        PageRequest pageRequest = PageRequest.of(0,1000, Sort.by("created_at").descending());
//        given(savingService.getSavingStatus(1L,1L,"1week", pageRequest))
//                .willReturn(savingStatusDto);
//
//        //when
//        ResultActions result = mockMvc.perform(
//                get("/api/v1/users/challenges/1/saving-histories").contentType(contentType)
//                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken)
//                        .param("period" , "1week")
//                        .param("ordering", "desc")
//        );
//        // then
//        result.andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.account_number", is("1111-2222")))
//        ;
//    }

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

package com.savle.togethersaving.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Role;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Controller test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("저축하기 성공")
    void savingApiTest() throws Exception {

        User user = User.builder()
                .userId(1L)
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

        Challenge challenge = Challenge.builder()
                .challengeId(1L)
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(100L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com")
                .build();

        CreateSavingsDto createSavingDto = CreateSavingsDto.builder()
                .challengePayment(5000L)
                .physicalAccountNumber("110-110")
                .cmaAccountNumber("220-220")
                .build();

        given(userService.saveMoney(1L,1L, createSavingDto)).willReturn(
                ResponseSavingsDto
                        .builder()
                        .amount(5000L)
                        .sendAccountNumber("110-110")
                        .sendAccountBankName("kakao")
                        .receiveAccountNumber("220-220")
                        .receiveAccountBankName("kakao-cma")
                        .build()
        );

        String content = objectMapper.writeValueAsString(createSavingDto);

        mockMvc.perform(post("/api/v1/users/challenges/1/saving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(userService).saveMoney(eq(1L), eq(1L),any());
    }
}
package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("User Controller test")
public class UserControllerTest extends ControllerTestUtil {


    @MockBean
    UserService userService;

    @Test
    @DisplayName("저축하기 성공")
    void savingApiTest() throws Exception {

        createUserAndChallenge();

        CreateSavingsDto createSavingDto = CreateSavingsDto.builder()
                .challengePayment(5000L)
                .physicalAccountNumber("110-110")
                .cmaAccountNumber("220-220")
                .build();


        String content = objectMapper.writeValueAsString(createSavingDto);

        mockMvc.perform(post("/api/v1/users/challenges/1/saving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(userService).saveMoney(eq(1L), eq(1L), any());
    }
}
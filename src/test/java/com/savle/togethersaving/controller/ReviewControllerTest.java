package com.savle.togethersaving.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.review.ReviewDto;
import com.savle.togethersaving.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("MemberController 테스트")
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ReviewService reviewService;

    @Test
    @DisplayName("리뷰 쓰기 성공")
    void addReviewApiTest() throws Exception {


        ReviewCreateDto reviewCreateDto = ReviewCreateDto.builder()
                .userId(1L)
                .challengeId(1L)
                .reviewContent("fun")
                .build();

        given(reviewService.saveReview(refEq(reviewCreateDto))).willReturn(
                ReviewDto.builder()
                        .challengeId(reviewCreateDto.getChallengeId())
                        .userId(reviewCreateDto.getUserId())
                        .reviewId(1L)
                        .content(reviewCreateDto.getReviewContent())
                        .writeDate(LocalDateTime.now())
                        .build()
        );

        String content = objectMapper.writeValueAsString(reviewCreateDto);

        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userId", is(1)));

        verify(reviewService).saveReview(refEq(reviewCreateDto));


    }


}

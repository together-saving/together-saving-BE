package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Review Controller test")
public class ReviewControllerTest extends ControllerTestUtil {


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
                ResponseReviewDto.builder()
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
                .andExpect(jsonPath("$.data.user_id", is(1)));

        verify(reviewService).saveReview(refEq(reviewCreateDto));


    }


}

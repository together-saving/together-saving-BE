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
import static org.mockito.ArgumentMatchers.any;
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

        createUserAndChallenge();

        ReviewCreateDto reviewCreateDto = ReviewCreateDto.builder()
                .challengeId(previousChallenge.getChallengeId())
                .reviewContent("fun")
                .build();


        String content = objectMapper.writeValueAsString(reviewCreateDto);

        mockMvc.perform(post("/api/v1/users/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        verify(reviewService).saveReview(any(Long.class),refEq(reviewCreateDto));


    }


}

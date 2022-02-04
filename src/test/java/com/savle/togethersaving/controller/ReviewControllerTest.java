package com.savle.togethersaving.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.savle.togethersaving.config.security.JwtProperties;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Review Controller test")
public class ReviewControllerTest extends ControllerTestUtil {


    @MockBean
    ReviewService reviewService;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("리뷰 쓰기 성공")
    void addReviewApiTest() throws Exception {

        createUserAndChallenge();


        createJwtToken();

        ReviewCreateDto reviewCreateDto = ReviewCreateDto.builder()
                .challengeId(challenge1.getChallengeId())
                .reviewContent("fun")
                .build();


        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(user);

        String content = objectMapper.writeValueAsString(reviewCreateDto);

        mockMvc.perform(post("/api/v1/users/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", JwtProperties.TOKEN_PREFIX + jwtToken))
                .andExpect(status().isOk());

        verify(reviewService).saveReview(any(Long.class), any(ReviewCreateDto.class));


    }


}

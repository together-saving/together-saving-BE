package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.review.ReviewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ReviewServiceTest extends TestUtil {


    @DisplayName("SaveReview() -리뷰 저장 성공")
    @Test
    void shouldSavedReviewSuccessfully() {

        //given
        createUserAndChallenge();

        //when
        ReviewDto savedReviewDto = reviewService.saveReview(user.getUserId(), reviewCreateDto);

        assertEquals(savedReviewDto.getChallengeId(), 1L);
        assertEquals(savedReviewDto.getReviewId(), 1L);
        assertEquals(savedReviewDto.getUserId(), 1L);
        assertEquals(savedReviewDto.getContent(), "즐겁네요");

    }

}
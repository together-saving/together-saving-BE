package com.savle.togethersaving.service.fixture;

import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.user.CreateSavingsDto;

public class DtoFixture {

    public static ReviewCreateDto reviewCreateDto;
    public static CreateSavingsDto createSavingDto;

    public static void createReviewDto(){
        reviewCreateDto = ReviewCreateDto.builder()
                .challengeId(1L)
                .reviewContent("즐겁네요")
                .build();
    }

    public static void createSavingDto(){
        createSavingDto = CreateSavingsDto.builder()
                .savingAmount(5000L)
                .build();
    }
}

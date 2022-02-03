package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.review.ChallengeReviewDto;
import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;


class ReviewServiceTest extends ServiceTestUtil {

    @Mock
    protected UserService userService;
    //@Mock
    //protected ChallengeService challengeService;
    @Mock
    protected ChallengeRepository challengeRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;


    @DisplayName("SaveReview() -리뷰 저장 성공")
    @Test
    void shouldSavedReviewSuccessfully() {

        createUserAndChallenge();
        createDtos();

        doReturn(user)
                .when(userService).getUserByUserId(user.getUserId());

        doReturn(challenge)
                .when(challengeRepository).getById(reviewCreateDto.getChallengeId());

        given(reviewRepository.save(any(ChallengeReview.class))).will(invocation -> ChallengeReview.builder()
                .reviewId(1L)
                .reviewer(user)
                .challenge(challenge)
                .content(reviewCreateDto.getReviewContent())
                .build());


        ResponseReviewDto savedReviewDto = reviewService.saveReview(user.getUserId(), reviewCreateDto);


        assertEquals(savedReviewDto.getChallengeId(), 1L);
        assertEquals(savedReviewDto.getReviewId(), 1L);
        assertEquals(savedReviewDto.getUserId(), 1L);
        assertEquals(savedReviewDto.getContent(), "즐겁네요");

    }

    @Test
    void mapToDto() {
        createUserAndChallenge();

        given(reviewRepository.findAllByChallenge_ChallengeId(challenge.getChallengeId()))
                .will(review ->
                        Arrays.asList(
                            ChallengeReview.builder()
                            .reviewId(1L)
                            .challenge(challenge)
                            .reviewer(user)
                            .content("hey")
                            .build()
                        )
                );

        List<ChallengeReviewDto> dtos = reviewService.reviewDtoOf(challenge.getChallengeId());
        assertEquals(dtos.size(), 1);
        assertEquals(dtos.get(0).getReviewerNickname(), user.getNickname());
        assertEquals(dtos.get(0).getContent(), "hey");
    }

}
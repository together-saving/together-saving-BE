package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.review.ChallengeReviewDto;
import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ReviewRepository;
import com.savle.togethersaving.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.savle.togethersaving.service.fixture.ChallengeFixture.challenge;
import static com.savle.togethersaving.service.fixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.service.fixture.DtoFixture.createReviewDto;
import static com.savle.togethersaving.service.fixture.DtoFixture.reviewCreateDto;
import static com.savle.togethersaving.service.fixture.UserFixture.createUser;
import static com.savle.togethersaving.service.fixture.UserFixture.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class ReviewServiceTest{

    @Mock
    protected UserRepository userRepository;
    @Mock
    protected ChallengeRepository challengeRepository;
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;


    @DisplayName("SaveReview() -리뷰 저장 성공")
    @Test
    void shouldSavedReviewSuccessfully() {

        createUser();
        createChallenge();
        createReviewDto();

        doReturn(user)
                .when(userRepository).getUserByUserId(user.getUserId());

        doReturn(challenge)
                .when(challengeRepository).getByChallengeId(reviewCreateDto.getChallengeId());

        given(reviewRepository.save(any(ChallengeReview.class))).will(invocation -> ChallengeReview.builder()
                .reviewId(1L)
                .reviewer(user)
                .challenge(challenge)
                .content(reviewCreateDto.getReviewContent())
                .build());


        reviewService.saveReview(user.getUserId(), reviewCreateDto);

        verify(reviewRepository, times(1)).save(any(ChallengeReview.class));

    }

    @Test
    void mapToDto() {
        createUser();
        createChallenge();

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
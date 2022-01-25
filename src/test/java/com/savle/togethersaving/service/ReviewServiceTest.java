package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    protected UserService userService;
    @Mock
    protected ChallengeService challengeService;
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;


    protected final User user = User.builder()
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

    protected final Challenge challenge = Challenge.builder()
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


    protected final ReviewCreateDto reviewCreateDto = ReviewCreateDto.builder()
            .userId(1L)
            .challengeId(1L)
            .reviewContent("즐겁네요")
            .build();


    @DisplayName("SaveReview() -리뷰 저장 성공")
    @Test
    void shouldSavedReviewSuccessfully() {


        doReturn(user)
                .when(userService).getUserByUserId(reviewCreateDto.getUserId());

        doReturn(challenge)
                .when(challengeService).getChallengeByChallengeId(reviewCreateDto.getChallengeId());

        given(reviewRepository.save(any(ChallengeReview.class))).will(invocation -> ChallengeReview.builder()
                .reviewId(1L)
                .reviewer(user)
                .challenge(challenge)
                .content(reviewCreateDto.getReviewContent())
                .build());


        ResponseReviewDto savedReviewDto = reviewService.saveReview(reviewCreateDto);


        assertEquals(savedReviewDto.getChallengeId(), 1L);
        assertEquals(savedReviewDto.getReviewId(), 1L);
        assertEquals(savedReviewDto.getUserId(), 1L);
        assertEquals(savedReviewDto.getContent(), "즐겁네요");

    }


}
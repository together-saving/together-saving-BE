package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final UserService userService;
    private final ChallengeService challengeService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ResponseReviewDto saveReview(Long userId,ReviewCreateDto reviewCreateDto) {

        User user = userService.getUserByUserId(userId);
        Challenge challenge = challengeService.getChallengeByChallengeId(reviewCreateDto.getChallengeId());

        ChallengeReview review = ChallengeReview.createReview(user,challenge,reviewCreateDto.getReviewContent());
        ChallengeReview savedReview = reviewRepository.save(review);

        savedReview.changeReviewListOfUser(user);
        savedReview.changeReviewListOfChallenge(challenge);


        return ResponseReviewDto.builder()
                .challengeId(savedReview.getChallenge().getChallengeId())
                .userId(savedReview.getReviewer().getUserId())
                .writeDate(savedReview.getCreatedAt())
                .reviewId(savedReview.getReviewId())
                .content(savedReview.getContent())
                .build();
    }
}

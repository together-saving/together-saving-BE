package com.savle.togethersaving.service;

import java.util.List;
import java.util.stream.Collectors;

import com.savle.togethersaving.dto.review.ChallengeReviewDto;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ReviewRepository;

import com.savle.togethersaving.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void saveReview(Long userId,ReviewCreateDto reviewCreateDto) {

        User user = userRepository.getUserByUserId(userId);
        Challenge challenge = challengeRepository.getByChallengeId(reviewCreateDto.getChallengeId());

        ChallengeReview review = ChallengeReview.createReview(user,challenge,reviewCreateDto.getReviewContent());
        ChallengeReview savedReview = reviewRepository.save(review);

        savedReview.changeReviewListOfUser(user);
        savedReview.changeReviewListOfChallenge(challenge);
    }

    public List<ChallengeReviewDto> reviewDtoOf(Long challengeId) {
        return reviewRepository.findAllByChallenge_ChallengeId(challengeId)
            .stream().map(review -> reviewDtoFrom(review)).collect(Collectors.toList());
    }

    private ChallengeReviewDto reviewDtoFrom(ChallengeReview review) {
        return ChallengeReviewDto.builder()
            .content(review.getContent())
            .reviewerNickname(review.getReviewer().getNickname())
            .profilePicture(review.getReviewer().getProfilePicture())
            .build();
    }
}

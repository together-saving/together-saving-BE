package com.savle.togethersaving.dto.review;

import com.savle.togethersaving.entity.ChallengeReview;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long userId;
    private Long challengeId;
    private Long reviewId;
    private LocalDateTime writeDate;
    private String content;

    public static ReviewDto toReviewDto(ChallengeReview review) {
        return ReviewDto.builder()
                .userId(review.getReviewer().getUserId())
                .challengeId(review.getChallenge().getChallengeId())
                .reviewId(review.getReviewId())
                .writeDate(review.getCreatedAt())
                .content(review.getContent())
                .build();
    }
}

package com.savle.togethersaving.dto.review;

import com.savle.togethersaving.entity.ChallengeReview;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseReviewDto {

    private Long userId;
    private Long challengeId;
    private Long reviewId;
    private LocalDateTime writeDate;
    private String content;


}

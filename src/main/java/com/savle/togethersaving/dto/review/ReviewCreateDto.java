package com.savle.togethersaving.dto.review;


import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeReview;
import com.savle.togethersaving.entity.User;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {

    private Long userId;
    private Long challengeId;
    private String reviewContent;

    public ChallengeReview toEntity(User user, Challenge challenge){
        return ChallengeReview
                .builder()
                .content(this.reviewContent)
                .challenge(challenge)
                .reviewer(user)
                .build();
    }
}

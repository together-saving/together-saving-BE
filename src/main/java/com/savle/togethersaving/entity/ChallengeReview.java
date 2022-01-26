package com.savle.togethersaving.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class ChallengeReview extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;


    @NotNull
    private String content;


    public static ChallengeReview createReview(User reviewer, Challenge challenge,String content) {

        return ChallengeReview.builder()
                .reviewer(reviewer)
                .challenge(challenge)
                .content(content)
                .build();

    }

    public void changeReviewListOfUser(User user){
        this.reviewer = user;
        this.reviewer.getReviewList().add(this);
    }

    public void changeReviewListOfChallenge(Challenge challenge){
        this.challenge = challenge;
        this.challenge.getChallengeReviewList().add(this);
    }
}

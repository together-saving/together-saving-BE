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
    @GeneratedValue
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable= false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable= false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;


    @NotNull
    private String content;

}

package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class ChallengeUser {

    @EmbeddedId
    private ChallengeUserPK challengeUserPK;


    private long accumulatedBalance;


    private Boolean isAutomated;

    private Integer savingRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("challengeId")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("userId")
    private User user;


    public void addBalance(Long amount) {
        this.accumulatedBalance += amount;
    }

}
package com.savle.togethersaving.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class ChallengeUser {

    @EmbeddedId
    private ChallengeUserPK challengeUserPK;


    private Long accumulatedBalance;


    private Boolean isAutomated; // 자동 저축 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("challengeId")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("userId")
    private User user;

    public ChallengeUser(ChallengeUserPK challengeUserPK, Long accumulatedBalance, Boolean isAutomated
            , Challenge challenge, User user) {
        this.challengeUserPK = new ChallengeUserPK();
        this.accumulatedBalance = 0L;
        this.isAutomated = false;
        this.challenge = challenge;
        this.user = user;
    }

    public void addBalance(Long amount){
        this.accumulatedBalance += amount;
    }
}
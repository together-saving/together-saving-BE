package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class ChallengeUser {

    @EmbeddedId
    private ChallengeUserPK challengeUserPK = new ChallengeUserPK();

    private Long accumulatedBalance;

    private Boolean isAutomated; // 자동 저축 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("challengeId")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("userId")
    private User user;
}
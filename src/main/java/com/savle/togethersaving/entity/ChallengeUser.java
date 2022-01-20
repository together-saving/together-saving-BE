package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
@IdClass(ChallengeTagPK.class)
public class ChallengeUser {
    @Id
    private Long challengeId;

    @Id
    private Long userId;

    private Long accumulatedBalance;

    private Boolean isAutomated; // 자동 저축 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User user;
}

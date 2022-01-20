package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@IdClass(ChallengeFrequencyPK.class)
public class ChallengeFrequency {

    @Id
    private Long challengeId;

    @Id
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;
}

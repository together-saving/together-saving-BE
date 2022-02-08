package com.savle.togethersaving.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ChallengeFrequency {
    @EmbeddedId
    private ChallengeFrequencyPK challengeFrequencyPK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("challengeId")
    private Challenge challenge;

    public ChallengeFrequency(ChallengeFrequencyPK challengeFrequencyPK, Challenge challenge) {
        this.challengeFrequencyPK = new ChallengeFrequencyPK();
        this.challenge = challenge;
    }
}
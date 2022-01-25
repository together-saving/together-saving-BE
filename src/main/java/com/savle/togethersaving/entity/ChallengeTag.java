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
public class ChallengeTag {
    @EmbeddedId
    private ChallengeTagPK challengeTagPK = new ChallengeTagPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("challengeId")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name", nullable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @MapsId("name")
    private Tag tag;

    public ChallengeTag(ChallengeTagPK challengeTagPK, Challenge challenge, Tag tag) {
        this.challengeTagPK = new ChallengeTagPK();
        this.challenge = challenge;
        this.tag = tag;
    }
}


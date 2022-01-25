package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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

    public ChallengeTag (ChallengeTagPK challengeTagPK, Challenge challenge, Tag tag) {
        this.challengeTagPK = new ChallengeTagPK();
        this.challenge = challenge;
        this.tag = tag;
    }
}
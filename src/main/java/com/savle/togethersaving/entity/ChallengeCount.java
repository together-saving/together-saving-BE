package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class ChallengeCount {

    @Id
    private Long challengeId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn(name = "challenge_id")
    private Challenge challenge;

    private Integer maxCount;
    private Integer remainCount;
    private Integer currentCount;
}

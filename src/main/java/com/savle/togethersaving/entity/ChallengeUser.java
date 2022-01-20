package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter

public class ChallengeUser {
    @Id
    private Long challengeId;

    @Id
    private Long userId;

    private Long accumulatedBalance;

    private Boolean isAutomated; // 자동 저축 여부

}

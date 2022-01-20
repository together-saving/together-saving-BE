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
public class ChallengeUser implements Serializable {
    @Id
    private Long challengeId;

    @Id
    @Column
    private Long userId;

    @Column
    private Long accumulatedBalance;

    @Column
    private Boolean isAutomated; // 자동 저축 여부

}

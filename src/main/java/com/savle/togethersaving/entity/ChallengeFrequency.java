package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class ChallengeFrequency {

    @Id
    private Long challengeId;

    @Id
    private Frequency frequency;


}

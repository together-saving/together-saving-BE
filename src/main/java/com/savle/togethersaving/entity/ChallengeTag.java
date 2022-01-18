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
public class ChallengeTag {

    @Id
    private Long challengeId;

    @Id
    private String name;
}

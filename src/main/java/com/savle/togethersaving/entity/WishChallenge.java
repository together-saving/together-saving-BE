package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class WishChallenge {

    @Id
    private String userId;

    @Id
    private String challengeId;
}

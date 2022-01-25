package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChallengeUserPK implements Serializable {

    private Long challengeId;

    private Long userId;

}
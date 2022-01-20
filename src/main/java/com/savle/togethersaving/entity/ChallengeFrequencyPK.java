package com.savle.togethersaving.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeFrequencyPK implements Serializable {

	private Long challengeId;

	private Frequency frequency;
}

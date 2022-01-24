package com.savle.togethersaving.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChallengeUserPK implements Serializable {

	private Long challengeId;

	private Long userId;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChallengeUserPK that = (ChallengeUserPK)o;
		return challengeId.equals(that.challengeId) && userId.equals(that.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(challengeId, userId);
	}
}

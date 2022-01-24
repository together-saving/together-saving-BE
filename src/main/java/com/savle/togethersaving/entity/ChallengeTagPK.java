package com.savle.togethersaving.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChallengeTagPK implements Serializable {

	private Long challengeId;

	private String name;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ChallengeTagPK that = (ChallengeTagPK)o;
		return challengeId.equals(that.challengeId) && name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(challengeId, name);
	}
}

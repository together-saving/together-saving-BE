package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.ChallengeTagPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeTagRepository extends JpaRepository<ChallengeTag, ChallengeTagPK> {
	List<ChallengeTag> findChallengeTagsByChallenge(Challenge challenge);
}

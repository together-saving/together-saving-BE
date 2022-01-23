package com.savle.togethersaving.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.ChallengeTagPK;

public interface ChallengeTagRepository extends PagingAndSortingRepository<ChallengeTag, ChallengeTagPK> {
	List<ChallengeTag> findChallengeTagsByChallenge(Challenge challenge);
}

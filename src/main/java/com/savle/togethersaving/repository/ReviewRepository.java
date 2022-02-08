package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ChallengeReview,Long> {
	List<ChallengeReview> findAllByChallenge_ChallengeId(Long challengeId);
}

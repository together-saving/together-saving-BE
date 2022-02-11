package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
	List<Review> findAllByChallenge_ChallengeId(Long challengeId);
}

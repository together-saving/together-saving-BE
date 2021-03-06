package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
	boolean existsByHopingPerson_UserIdAndChallenge_ChallengeId(Long userId, Long challengeId);
	Optional<Wish> findWishByChallenge_ChallengeIdAndHopingPerson_UserId(Long challengeId, Long userId);
}

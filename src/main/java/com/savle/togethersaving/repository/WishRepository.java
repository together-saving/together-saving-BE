package com.savle.togethersaving.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
	boolean existsByHopingPerson_UserIdAndChallenge_ChallengeId(Long userId, Long challengeId);
	Optional<Wish> findWishByChallenge_ChallengeIdAndHopingPerson_UserId(Long challengeId, Long userId);
}

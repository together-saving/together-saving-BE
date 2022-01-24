package com.savle.togethersaving.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
	boolean existsByHopingPerson_UserIdAndChallenge(Long userId, Challenge challenge);
}

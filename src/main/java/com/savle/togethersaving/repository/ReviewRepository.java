package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ChallengeReview,Long> {

}

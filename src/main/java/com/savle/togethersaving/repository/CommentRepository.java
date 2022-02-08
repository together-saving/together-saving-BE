package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<ChallengeComment,Long> {
}

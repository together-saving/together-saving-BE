package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<ChallengeComment, Long> {

    @Query("select cmm from ChallengeComment cmm join fetch cmm.writer where cmm.challenge.challengeId=:challengeId " )
    List<ChallengeComment> findCommentFrom(@Param("challengeId") long challengeId);
}

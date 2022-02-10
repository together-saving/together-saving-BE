package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select cmm from Comment cmm join fetch cmm.writer where cmm.challenge.challengeId=:challengeId " )
    List<Comment> findCommentFrom(@Param("challengeId") Long challengeId);
}

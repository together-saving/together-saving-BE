package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<ChallengeComment, Long> {

    @Query(value = "select *"
            + "from challenge_comment "
            + "where date(created_at) = date_sub(CURDATE(), INTERVAL :offset day ) "
            + "and challenge_id = :challengeId"
            , nativeQuery = true)
    List<ChallengeComment> findCommentFrom(@Param("challengeId") long challengeId, @Param("offset") int offset);
}

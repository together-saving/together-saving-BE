package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeComment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryTest extends RepositoryTestUtil{

    @Test
    void findComment() {
        createUserAndChallengeSaved();
        ChallengeComment comment = ChallengeComment.builder()
                .challenge(previousChallenge)
                .writer(user)
                .content("test").build();
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        //List<ChallengeComment> comments = commentRepository.findCommentFrom(previousChallenge.getChallengeId(), 0);
        //Assertions.assertThat(comments.size()).isEqualTo(1);
        //Assertions.assertThat(comments.get(0).getWriter().getUserId()).isEqualTo(user.getUserId());
    }
}
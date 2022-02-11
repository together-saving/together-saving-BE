package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeComment;
import com.savle.togethersaving.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.CommentFixture.createComment;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;

@DataJpaTest
class ChallengeCommentRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findComment() {
        // given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        commentRepository.saveAll(createComment(savedUser,savedChallenge));

        //when
        List<ChallengeComment> challengeComments = commentRepository.findCommentFrom(savedChallenge.getChallengeId());

        //then
        Assertions.assertThat(challengeComments.size()).isEqualTo(10);
        Assertions.assertThat(challengeComments.get(0).getWriter().getUserId()).isEqualTo(savedUser.getUserId());
    }
}
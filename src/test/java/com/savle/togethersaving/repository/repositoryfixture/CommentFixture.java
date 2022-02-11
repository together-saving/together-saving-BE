package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeComment;
import com.savle.togethersaving.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CommentFixture {

    public static List<ChallengeComment> createComment(User writer, Challenge challenge) {

        List<ChallengeComment> challengeCommentList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            challengeCommentList.add(
                    ChallengeComment.builder()
                            .writer(writer)
                            .challenge(challenge)
                            .content("시작해봅시다!" + i)
                            .build()
            );
        }

        return challengeCommentList;

    }
}

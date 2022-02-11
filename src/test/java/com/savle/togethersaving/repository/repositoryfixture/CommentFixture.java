package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Comment;
import com.savle.togethersaving.entity.User;

import java.util.ArrayList;
import java.util.List;

public class CommentFixture {

    public static List<Comment> createComment(User writer, Challenge challenge) {

        List<Comment> commentList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            commentList.add(
                    Comment.builder()
                            .writer(writer)
                            .challenge(challenge)
                            .content("시작해봅시다!" + i)
                            .build()
            );
        }

        return commentList;

    }
}

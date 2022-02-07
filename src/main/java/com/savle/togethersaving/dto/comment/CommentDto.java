package com.savle.togethersaving.dto.comment;

import com.savle.togethersaving.entity.ChallengeComment;
import com.savle.togethersaving.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private long userId;
    private boolean writtenByMe;
    private String profilePicture;
    private String nickname;
    private String content;

    public static CommentDto of(long client, User commentWriter, ChallengeComment comment) {
        CommentDto dto = new CommentDto();
        dto.userId = commentWriter.getUserId();
        dto.nickname = commentWriter.getNickname();
        dto.profilePicture = commentWriter.getProfilePicture();
        dto.content = comment.getContent();
        dto.writtenByMe = dto.isWrittenByMe(client);
        return dto;
    }

    public boolean isWrittenByMe(long userId) {
        return this.userId == userId;
    }
}

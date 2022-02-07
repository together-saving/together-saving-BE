package com.savle.togethersaving.dto.comment;

import com.savle.togethersaving.entity.ChallengeComment;
import com.savle.togethersaving.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private long userId;
    private String profilePicture;
    private String nickname;
    private String content;

    public static CommentDto of(User user, ChallengeComment comment) {
        CommentDto dto = new CommentDto();
        dto.userId = user.getUserId();
        dto.nickname = user.getNickname();
        dto.profilePicture = user.getProfilePicture();
        dto.content = comment.getContent();
        return dto;
    }
}

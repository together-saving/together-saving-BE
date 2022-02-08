package com.savle.togethersaving.dto.comment;

import com.savle.togethersaving.entity.ChallengeComment;
import com.savle.togethersaving.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CommentDto {

    private LocalDate date;
    private List<Comment> comments;

    @Data
    public static class Comment {
        private long userId;
        private boolean writtenByMe;
        private String profilePicture;
        private String nickname;
        private String content;
        private LocalDate createdAt;

        public static Comment of(long clientId, User commentWriter, ChallengeComment comment) {
            Comment dto = new Comment();
            dto.userId = commentWriter.getUserId();
            dto.nickname = commentWriter.getNickname();
            dto.profilePicture = commentWriter.getProfilePicture();
            dto.content = comment.getContent();
            dto.writtenByMe = dto.isWrittenByMe(clientId);
            dto.setCreatedAt(comment.getCreatedAt().toLocalDate());
            return dto;
        }
        private boolean isWrittenByMe(long userId) {
            return this.userId == userId;
        }
    }


}

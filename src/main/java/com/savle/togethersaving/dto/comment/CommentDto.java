package com.savle.togethersaving.dto.comment;

import com.savle.togethersaving.entity.Comment;
import com.savle.togethersaving.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CommentDto {

    private LocalDate date;
    private List<ChallengeComment> comments;

    @Data
    public static class ChallengeComment {
        private long userId;
        private boolean writtenByMe;
        private String profilePicture;
        private String nickname;
        private String content;
        private LocalDate createdAt;

        public static ChallengeComment of(long clientId, User commentWriter, Comment comment) {
            ChallengeComment dto = new ChallengeComment();
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

package com.savle.togethersaving.dto.comment;

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
    private List<ChallengeComment> challengeComments;

    @Data
    public static class ChallengeComment {
        private long userId;
        private boolean writtenByMe;
        private String profilePicture;
        private String nickname;
        private String content;

        public static ChallengeComment of(long clientId, User commentWriter, com.savle.togethersaving.entity.ChallengeComment challengeComment) {
            ChallengeComment dto = new ChallengeComment();
            dto.userId = commentWriter.getUserId();
            dto.nickname = commentWriter.getNickname();
            dto.profilePicture = commentWriter.getProfilePicture();
            dto.content = challengeComment.getContent();
            dto.writtenByMe = dto.isWrittenByMe(clientId);
            return dto;
        }
        private boolean isWrittenByMe(long userId) {
            return this.userId == userId;
        }
    }


}

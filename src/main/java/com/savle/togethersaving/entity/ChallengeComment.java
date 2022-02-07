package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class ChallengeComment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;

    private String content;

    public static ChallengeComment createComment(User writer, Challenge challenge,String content) {

        return ChallengeComment.builder()
                .writer(writer)
                .challenge(challenge)
                .content(content)
                .build();

    }

    public void changeCommentListOfUser(User user){
        this.writer = user;
        this.writer.getCommentList().add(this);
    }

    public void changeCommentListOfChallenge(Challenge challenge){
        this.challenge = challenge;
        this.challenge.getCommentList().add(this);
    }

}

package com.savle.togethersaving.entity;

import com.savle.togethersaving.config.ModeConverter;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private String title;

    @Column(length = 900)
    private String content;

    private Long payment;

    private Long members;

    @Convert(converter = ModeConverter.class)
    private Mode mode;

    private Long entryFee;

    private int period;

    private Boolean isActive;

    private LocalDate endDate;

    private String thumbnail;

    @OneToOne(mappedBy = "challenge" )
    private ChallengeCount challengeCount;

    @OneToMany(mappedBy = "challenge")
    private final List<ChallengeReview> challengeChallengeReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private final List<TransactionLog> logList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY)
    private final List<ChallengeTag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private final List<ChallengeComment> challengeCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge",fetch = FetchType.LAZY)
    private final List<ChallengeFrequency> days = new ArrayList<>();

    public void addMember(){
        this.members ++;
    }
}
package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.NotNull;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Challenge {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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

    @Enumerated(EnumType.STRING)
    private Mode mode;

    private Long entryFee;

    private int period;

    private String thumbnail;

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeReview> challengeReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<TransactionLog> logList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeTag> tagList = new ArrayList<>();
}
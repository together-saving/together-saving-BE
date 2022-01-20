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

    @GeneratedValue
    @Id
    private Long challengeId;


    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private String title;

    private String content;

    private Long payment;

    private Long members;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    private Long entryFee;

    private int period;

    private String thumbnail;

    @OneToMany(mappedBy = "challengeId")
    private List<ChallengeReview> reviewList = new ArrayList<>();
}
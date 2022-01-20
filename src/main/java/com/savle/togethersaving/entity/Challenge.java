package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Challenge {

    @GeneratedValue
    @Id
    @Column(name = "challenge_id")
    private Long challengeId;

    @Column(name = "host_id", nullable = false)
    private Long hostId;

    private LocalDate startDate;

    private String title;

    private String content;

    private Long payment;

    private Long members;

    private Mode mode;

    private Long entryFee;

    private int period;

    private String thumbnail;


}

package com.savle.togethersaving.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private LocalDate birth;

    @NotNull
    private Boolean gender;

    @NotNull
    private String phoneNumber;

    private String profilePicture;

    @ColumnDefault("'닉네임을 설정해주세요.'")
    private String nickname;

    private String role;

    @ColumnDefault("0")
    private Long point;

    @ColumnDefault("0")
    private Long reward;

    @OneToMany(mappedBy = "owner")
    private final List<Account> accountList = new ArrayList<>();

    @OneToMany(mappedBy = "hopingPerson")
    private final List<Wish> wishedChallengesList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewer")
    private final List<ChallengeReview> reviewList = new ArrayList<>();

}
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
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    private String email;

    @NotNull
    private LocalDate birth;

    @NotNull
    private Boolean gender;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "nickname")
    @ColumnDefault("닉네임을 설정해주세요.")
    private String nickName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("`USER`")
    private Role role;

    @ColumnDefault("0")
    private Long point;

    @ColumnDefault("0")
    private Long reward;

    @OneToMany(mappedBy = "ownerId")
    private List<Account> accounts = new ArrayList<>();
}

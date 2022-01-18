package com.savle.togethersaving.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String email;

    @Column
    private String birth;

    @Column
    private Boolean gender;

    @Column
    private String phoneNumber;

    @Column
    private String profilePicture;

    @Column
    private String nickName;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAdmin;

    @Column
    private Long point;

    @Column
    private Long reward;
}

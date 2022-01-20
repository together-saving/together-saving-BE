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
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    private String email;

    private String birth;

    private Boolean gender;

    private String phoneNumber;

    private String profilePicture;

    private String nickName;

    private Boolean isAdmin;

    private Long point;

    private Long reward;
}

package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.User;

import java.time.LocalDate;

public class UserFixture {

    public static User createUser() {

        return User.builder()
                .email("sheep@naver.com")
                .birth(LocalDate.of(2020, 01, 01))
                .gender(true)
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.com")
                .nickname("NICK")
                .role("user")
                .point(0L)
                .reward(0L)
                .password("password")
                .build();
    }

    public User createAdmin() {
        return User.builder()
                .email("admin@naver.com")
                .birth(LocalDate.of(2021, 11, 11))
                .gender(true)
                .phoneNumber("010-5678-1234")
                .profilePicture("http://qweqweqweqwe.com")
                .nickname("Admin-NICK")
                .role("ADMIN")
                .point(0L)
                .reward(0L)
                .password("password")
                .build();
    }
}

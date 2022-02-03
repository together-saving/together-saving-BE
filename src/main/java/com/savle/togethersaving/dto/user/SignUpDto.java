package com.savle.togethersaving.dto.user;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String email;
    private String password;
    private String role;
}

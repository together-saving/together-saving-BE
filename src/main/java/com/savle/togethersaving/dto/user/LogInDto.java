package com.savle.togethersaving.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {

    private String email;
    private String password;
}

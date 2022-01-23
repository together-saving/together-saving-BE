package com.savle.togethersaving.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private T data;

    public static <T> Result<T> AllChallengeListOK(T data) {
        return Result.<T>builder()
                .data(data)
                .build();
    }
}

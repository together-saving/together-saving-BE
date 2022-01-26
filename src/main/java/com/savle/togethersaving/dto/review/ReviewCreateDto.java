package com.savle.togethersaving.dto.review;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {

    private Long challengeId;
    private String reviewContent;

}

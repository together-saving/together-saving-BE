package com.savle.togethersaving.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChallengeReviewDto {

	private String reviewerNickname;
	private String content;
	private String profilePicture;
}

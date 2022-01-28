package com.savle.togethersaving.dto.challenge;

import static java.time.temporal.ChronoUnit.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savle.togethersaving.dto.review.ChallengeReviewDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Frequency;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Tag;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeDetailDto {
	private Long challengeId;

	@JsonProperty(value="is_wished")
	private boolean isWished;

	private String thumbnail;

	private String challengeName;

	private Mode mode;

	private int period;

	private LocalDate startDate;

	private LocalDate endDate;

	private Long challengePayment;

	private Long challengeEntryFee;

	private Long challengeMembers;

	private String hostNickname;

	private String challengeDescription;

	private List<Tag> tags;

	private List<Frequency> challengeFrequency;

	private List<ChallengeReviewDto> challengeReviews;

	private boolean isParticipated;

	public static ChallengeDetailDto challengeOf(Challenge challenge) {
		ChallengeDetailDto dto = new ChallengeDetailDto();
		dto.challengeId = challenge.getChallengeId();
		dto.startDate = challenge.getStartDate();
		dto.endDate = findEndDate(challenge);
		dto.challengeName = challenge.getTitle();
		dto.mode = challenge.getMode();
		dto.period = challenge.getPeriod();
		dto.thumbnail = challenge.getThumbnail();
		dto.hostNickname = challenge.getHost().getNickname();
		dto.challengeDescription = challenge.getContent();
		dto.challengeEntryFee = challenge.getEntryFee();
		return dto;
	}

	private static LocalDate findEndDate(Challenge challenge) {
		return challenge.getStartDate().plusDays(challenge.getPeriod()*7);
	}
}

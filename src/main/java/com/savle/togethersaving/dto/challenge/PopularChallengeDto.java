package com.savle.togethersaving.dto.challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Setter
public class PopularChallengeDto {
	private Long id;

	@JsonProperty(value="is_wished")
	private boolean isWished;

	private String thumbnail;

	private String title;

	private Mode mode;

	private int period;

	private long remainDate;

	private List<String> tags;

	public static PopularChallengeDto challengeOf(Challenge challenge) {
		PopularChallengeDto dto = new PopularChallengeDto();
		dto.id = challenge.getChallengeId();
		dto.remainDate = DAYS.between(LocalDate.now(), challenge.getStartDate());
		dto.title = challenge.getTitle();
		dto.mode = challenge.getMode();
		dto.period = challenge.getPeriod();
		dto.thumbnail = challenge.getThumbnail();
		return dto;
	}
}

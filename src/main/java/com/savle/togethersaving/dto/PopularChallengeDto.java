package com.savle.togethersaving.dto;

import static java.time.temporal.ChronoUnit.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Tag;

import lombok.Getter;
import lombok.Setter;

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

	private List<Tag> tags;

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

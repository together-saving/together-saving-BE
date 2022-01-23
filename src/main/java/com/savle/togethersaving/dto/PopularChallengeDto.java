package com.savle.togethersaving.dto;

import static java.time.temporal.ChronoUnit.*;

import java.time.LocalDate;
import java.util.List;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Tag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularChallengeDto {
	private Long id;

	private boolean isWished;

	private long remainDate;

	private String title;

	private Mode mode;

	private int period;

	private String thumbnail;

	private List<Tag> tags;

	public static PopularChallengeDto challengeOf(Challenge challenge) {
		PopularChallengeDto dto = new PopularChallengeDto();
		dto.id = challenge.getChallengeId();
		dto.remainDate = DAYS.between(challenge.getStartDate(), LocalDate.now());
		dto.title = challenge.getTitle();
		dto.mode = challenge.getMode();
		dto.period = challenge.getPeriod();
		dto.thumbnail = challenge.getThumbnail();
		return dto;
	}
}

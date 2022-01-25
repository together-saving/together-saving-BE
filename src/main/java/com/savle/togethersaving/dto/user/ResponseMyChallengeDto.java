package com.savle.togethersaving.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savle.togethersaving.dto.PopularChallengeDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Builder
@Getter
@Setter
public class ResponseMyChallengeDto {

    private Long id;

    private String thumbnail;

    private String title;

    private Mode mode;

    private int period;

    // 챌린지 종료까지 남은 기간
    // 시작 날짜 - 현재 날짜
    private long date;

    private List<Tag> tags;

    public static ResponseMyChallengeDto toDto(Challenge challenge) {

       return ResponseMyChallengeDto.builder()
                .id(challenge.getChallengeId())
                .thumbnail(challenge.getThumbnail())
                .title(challenge.getTitle())
                .mode(challenge.getMode())
                .period(challenge.getPeriod())
                .date(ChronoUnit.DAYS.between(challenge.getStartDate(), LocalDate.now()))
                .build();
    }
}

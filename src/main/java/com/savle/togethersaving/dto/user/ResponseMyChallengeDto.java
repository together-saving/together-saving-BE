package com.savle.togethersaving.dto.user;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Mode;
import com.savle.togethersaving.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Builder
@Getter
@Setter
public class ResponseMyChallengeDto {

    private Long id;

    private String thumbnail;

    private String title;

    private Mode mode;

    private int period;


    private long date;

    private List<String> tags;

    public static ResponseMyChallengeDto toDto(Challenge challenge) {

        // 시작 날짜 + 진행기간(주차) - currentDay
        LocalDate date=  challenge.getStartDate().plusWeeks(challenge.getPeriod());

       return ResponseMyChallengeDto.builder()
                .id(challenge.getChallengeId())
                .thumbnail(challenge.getThumbnail())
                .title(challenge.getTitle())
                .mode(challenge.getMode())
                .period(challenge.getPeriod())
                .date(ChronoUnit.DAYS.between(LocalDate.now(),date))
                .build();
    }
}

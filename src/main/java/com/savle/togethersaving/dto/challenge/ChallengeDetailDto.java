package com.savle.togethersaving.dto.challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savle.togethersaving.dto.review.ChallengeReviewDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Frequency;
import com.savle.togethersaving.entity.Mode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ChallengeDetailDto {
    private Long challengeId;

    @JsonProperty(value = "is_wished")
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
    private String hostThumbnail;
    private String challengeDescription;

    private List<String> tags;

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
        dto.challengePayment = challenge.getPayment();
        dto.challengeMembers = challenge.getMembers();
        dto.period = challenge.getPeriod();
        dto.thumbnail = challenge.getThumbnail();
        dto.hostNickname = challenge.getHost().getNickname();
        dto.setHostThumbnail(challenge.getHost().getProfilePicture());
        dto.challengeDescription = challenge.getContent();
        dto.challengeEntryFee = challenge.getEntryFee();
        return dto;
    }

    private static LocalDate findEndDate(Challenge challenge) {
        return challenge.getStartDate().plusDays(challenge.getPeriod() * 7);
    }
}

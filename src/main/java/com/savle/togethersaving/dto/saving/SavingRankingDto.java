package com.savle.togethersaving.dto.saving;

import com.savle.togethersaving.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SavingRankingDto {
    private Long userId;
    private String profilePicture;
    private String nickName;
    private Integer savingRate;

    public static SavingRankingDto userFrom(User user) {
        SavingRankingDto savingRankingDto = new SavingRankingDto();
        savingRankingDto.setUserId(user.getUserId());
        savingRankingDto.setProfilePicture(user.getProfilePicture());
        savingRankingDto.setNickName(user.getNickname());
        return savingRankingDto;
    }
}

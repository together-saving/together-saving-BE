package com.savle.togethersaving.dto.saving;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SavingDetailDto {
    private String thumbnail;
    private Long accumualtedAmount;
    private String nickname;
    private Integer savingRate;
    private Integer successCount;
    private Integer failureCount;
    private Integer remainCount;
}

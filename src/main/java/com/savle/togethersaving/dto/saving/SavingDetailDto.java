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
    private Integer dDay;
    private Integer savingRate;
    private Integer successCount;
    private Integer failureCount;
    private Integer remainCount;
}

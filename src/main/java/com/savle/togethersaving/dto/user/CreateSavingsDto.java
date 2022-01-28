package com.savle.togethersaving.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSavingsDto {
    private Long challengePayment;
    private String physicalAccountNumber;
    private String cmaAccountNumber;
    private Long savingAmount;
}

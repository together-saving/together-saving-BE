package com.savle.togethersaving.dto.user;


import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseSavingsDto {

    private Long amount;
    private String sendAccountNumber;
    private String sendAccountBankName;
    private String receiveAccountNumber;
    private String receiveAccountBankName;
}

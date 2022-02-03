package com.savle.togethersaving.dto.saving;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SavingStatusDto {
    private String  accountNumber;
    private String  bankName;
    private String  thumbnail;
    private Long    balance;
    private Boolean isAutomated;
    private List<History>   savingHistory;

    @Builder
    @ToString
    @Getter
    @Setter
    public static class History {
        @Column(name = "created_at")
        private LocalDate   date;
        private DayOfWeek   dayOfWeek;
        @Column(name = "amount")
        private Long        amount;
    }
}

package com.savle.togethersaving.dto.saving;

import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.TransactionLog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class SavingStatusDto {
    private String  accountNumber;
    private String  bankName;
    private String  thumbnail;
    private Long    balance;
    private Boolean isAutomated;
    private List<History>   savingHistory;

    @ToString
    @Getter
    @Setter
    public static class History {
        @Column(name = "created_at")
        private LocalDate   date;
        private DayOfWeek   dayOfWeek;
        @Column(name = "amount")
        private Long        amount;

        public static History from(TransactionLog tx) {
            History history = new History();
            history.setAmount(tx.getAmount());
            history.setDate(tx.getCreatedAt().toLocalDate());
            history.setDayOfWeek(tx.getCreatedAt().getDayOfWeek());
            return history;
        }
    }

    public static SavingStatusDto of(Account account , ChallengeUser challengeUser, List<TransactionLog> transactionLogs) {
        SavingStatusDto savingStatusDto = new SavingStatusDto();
        savingStatusDto.setAccountNumber(account.getAccountNumber());
        savingStatusDto.setBalance(account.getBalance());
        savingStatusDto.setBankName(account.getBankName());
        savingStatusDto.setThumbnail(account.getThumbnail());
        savingStatusDto.setIsAutomated(challengeUser.getIsAutomated());
        savingStatusDto.setSavingHistory(transactionLogs.stream().map(History::from).collect(Collectors.toList()));
        return savingStatusDto;
    }
}

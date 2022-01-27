package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.entity.Account;
import com.savle.togethersaving.entity.AccountType;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.AccountRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.repository.TransactionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SavingService {
    private final  AccountRepository accountRepository;
    private final ChallengeUserRepository challengeUserRepository;
    private final TransactionLogRepository transactionLogRepository;


    public SavingStatusDto getSavingStatus(Long userId, Long challengeId) {
        Account account = accountRepository.findAccountByOwner_UserIdAndAccountType(userId, AccountType.PHYSICAL);
        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(userId, challengeId);
        List<TransactionLog> transactionLogs = transactionLogRepository.getSavingHistorys(userId,challengeId);
        List<SavingStatusDto.History> histories = transactionLogs.stream().map(this::txLogToHistroty).collect(Collectors.toList());

        return SavingStatusDto.builder()
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .bankName(account.getBankName())
                .thumbnail(account.getThumbnail())
                .isAutomated(challengeUser.getIsAutomated())
                .savingHistory(histories)
                .build();
    }

    public SavingStatusDto.History txLogToHistroty(TransactionLog tx) {
        return SavingStatusDto.History.builder()
                .amount(tx.getAmount())
                .dayOfWeek(tx.getCreatedAt().getDayOfWeek())
                .date(tx.getCreatedAt().toLocalDate()).build();
    }

}

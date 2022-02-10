package com.savle.togethersaving.service.servicefixture;

import com.savle.togethersaving.entity.TransactionLog;

import static com.savle.togethersaving.service.servicefixture.AccountFixture.receiveAccount;
import static com.savle.togethersaving.service.servicefixture.AccountFixture.sendAccount;
import static com.savle.togethersaving.service.servicefixture.ChallengeFixture.challenge;
import static com.savle.togethersaving.service.servicefixture.DtoFixture.createSavingDto;

public class TransactionLogFixture {

    public static TransactionLog saveTransactionLog;
    public static TransactionLog payTransactionLog;

    public static void createTransactionLog() {
        saveTransactionLog = TransactionLog.builder()
                .logId(1L)
                .challenge(challenge)
                .amount(createSavingDto.getSavingAmount())
                .sendAccount(sendAccount)
                .receiveAccount(receiveAccount)
                .build();

        payTransactionLog = TransactionLog.builder()
                .logId(2L)
                .challenge(challenge)
                .amount(challenge.getEntryFee())
                .sendAccount(sendAccount)
                .receiveAccount(receiveAccount)
                .build();
    }
}

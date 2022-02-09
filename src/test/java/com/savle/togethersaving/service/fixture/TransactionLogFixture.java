package com.savle.togethersaving.service.fixture;

import com.savle.togethersaving.entity.TransactionLog;

import static com.savle.togethersaving.service.fixture.AccountFixture.receiveAccount;
import static com.savle.togethersaving.service.fixture.AccountFixture.sendAccount;
import static com.savle.togethersaving.service.fixture.ChallengeFixture.challenge;
import static com.savle.togethersaving.service.fixture.DtoFixture.createSavingDto;

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

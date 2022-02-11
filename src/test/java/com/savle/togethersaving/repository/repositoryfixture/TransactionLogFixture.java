package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionLogFixture {

    public static List<TransactionLog> createTransactionList(Account sendAccount, Account receiveAccount,Challenge challenge) {

        List<TransactionLog> transactionLogList = new ArrayList<>();

        for(int i = 1; i <= 10; i++){

            transactionLogList.add(TransactionLog.builder()
                    .challenge(challenge)
                    .amount((long) (i*100))
                    .sendAccount(sendAccount)
                    .receiveAccount(receiveAccount)
                    .build());
        }
        return transactionLogList;
    }
}

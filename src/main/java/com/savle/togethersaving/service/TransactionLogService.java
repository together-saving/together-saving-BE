package com.savle.togethersaving.service;


import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;

    @Transactional
    public void saveTransaction(TransactionLog transactionLog){

        transactionLogRepository.save(transactionLog);
    }
}

package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepository extends JpaRepository<TransactionLog,Long> {
}

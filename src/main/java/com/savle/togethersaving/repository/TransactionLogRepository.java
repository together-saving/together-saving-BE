package com.savle.togethersaving.repository;

import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.entity.TransactionLog;
import com.savle.togethersaving.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionLogRepository extends JpaRepository<TransactionLog,Long> {
    @Query(nativeQuery = true, value = "select tx.* from transaction_log tx inner join account ac " +
            "on tx.send_account = ac.account_number where tx.challenge_id = :challenge and ac.user_id = :user and not tx.receive_account = '000-0000-0000'")
    List<TransactionLog> getSavingHistorys(Long user, Long challenge);
}
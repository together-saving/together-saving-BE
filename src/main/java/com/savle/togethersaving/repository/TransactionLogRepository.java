package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.TransactionLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
    @Query(value = "select tx.* from transaction_log tx inner join account ac " +
            "on tx.send_account = ac.account_number where tx.challenge_id = :challenge " +
            "and ac.user_id = :user and not tx.receive_account = '000-0000-0000'" +
            "and date_add(curdate() , interval - :period day ) <= date(created_at)" +
            "and date(created_at) <= curdate() "
            , nativeQuery = true)
    List<TransactionLog> getSavingHistorys(@Param("user") Long user, @Param("challenge") Long challenge,
                                           @Param("period") Integer period , Pageable pageable);
}
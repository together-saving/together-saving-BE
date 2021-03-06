package com.savle.togethersaving.entity;

import com.savle.togethersaving.config.AccountConverter;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Account {

    @Id
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User owner;

    @NotNull
    private Long balance;


    @NotNull
    @Convert(converter = AccountConverter.class)
    private AccountType accountType;


    @NotNull
    private String bankName;

    private String thumbnail;


    @OneToMany(mappedBy = "sendAccount")
    private final List<TransactionLog> sendLogList = new ArrayList<>();

    @OneToMany(mappedBy = "receiveAccount")
    private final List<TransactionLog> receiveLogList = new ArrayList<>();

    public void withdraw(Long withdrawalAmount) {
        this.balance -= withdrawalAmount;
    }

    public void deposit(Long depositAmount) {
        this.balance += depositAmount;
    }
}

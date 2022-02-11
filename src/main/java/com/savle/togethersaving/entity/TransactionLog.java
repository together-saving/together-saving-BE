package com.savle.togethersaving.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@ToString
public class TransactionLog extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;

    @NotNull
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_account", nullable = false)
    private Account sendAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_account", nullable = false)
    private Account receiveAccount;


    public void addSendAccountLog(Account sendAccount) {
        this.sendAccount = sendAccount;
        this.sendAccount.getSendLogList().add(this);
    }

    public void addReceiveAccountLog(Account receiveAccount) {
        this.receiveAccount = receiveAccount;
        this.receiveAccount.getReceiveLogList().add(this);
    }

    public void addChallengeLog(Challenge challenge) {
        this.challenge = challenge;
        this.challenge.getLogList().add(this);
    }
}

package com.savle.togethersaving.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class TransactionLog extends BaseTime{

	@Id
	@GeneratedValue
	private Long logId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="challenge_id")
	private Challenge challengeId;


	private LocalDate sendDate;

	private LocalDateTime txTime;

	private Long amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="send_account")
	private Account sendAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="receive_account")
	private Account receiveAccount;
}

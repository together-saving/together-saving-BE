package com.savle.togethersaving.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
public class TransactionLog {

	@Id
	@GeneratedValue
	private Long logId;

	private Long challengeId;

	private LocalDate sendDate;

	private LocalDateTime txTime;

	private Long amount;

	private String sendAccount;

	private String receiveAccount;
}

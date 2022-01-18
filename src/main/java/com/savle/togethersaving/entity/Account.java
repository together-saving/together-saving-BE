package com.savle.togethersaving.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Account {

	@Id
	@GeneratedValue
	private String accountNumber;

	private Long userId;

	private Long balance;

	private AccountType accountType;

	private String bankName;

	private String thumbnail;
}

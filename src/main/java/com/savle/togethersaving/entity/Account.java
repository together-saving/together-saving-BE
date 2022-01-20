package com.savle.togethersaving.entity;

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
public class Account {

	@Id
	@GeneratedValue
	@Column(name="account_number")
	private String accountNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User ownerId;

	private Long balance;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private String bankName;

	private String thumbnail;


}

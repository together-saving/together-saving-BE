package com.savle.togethersaving.entity;

import javax.persistence.*;

import com.savle.togethersaving.config.AccountConverter;
import com.savle.togethersaving.config.RoleConverter;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@JoinColumn(name="user_id", nullable= false, insertable = false
			, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private User owner;

	@NotNull
	private Long balance;

	@NotNull
	@Convert(converter = AccountConverter.class)
	private AccountType accountType;

	@NotNull
	private String bankName;

	private String thumbnail;

	@OneToMany(mappedBy = "logId")
	private final List<TransactionLog> logList = new ArrayList<>();

	public void withdraw(Long withdrawalAmount){
		this.balance -= withdrawalAmount;
	}

	public void deposit(Long depositAmount){
		this.balance += depositAmount;
	}
}

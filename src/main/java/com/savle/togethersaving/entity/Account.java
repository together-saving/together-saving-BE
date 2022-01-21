package com.savle.togethersaving.entity;

import javax.persistence.*;

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
	@GeneratedValue
	private String accountNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable= false, insertable = false)
	private User owner;

	@NotNull
	private Long balance;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@NotNull
	private String bankName;

	private String thumbnail;

	@OneToMany(mappedBy = "logId")
	private List<TransactionLog> logList = new ArrayList<>();
}

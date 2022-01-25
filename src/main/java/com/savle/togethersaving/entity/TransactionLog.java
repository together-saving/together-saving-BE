package com.savle.togethersaving.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.sun.istack.NotNull;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="challenge_id", nullable= false, insertable = false
			, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private Challenge challenge;

	@NotNull
	private Long amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="send_account", nullable= false, insertable = false)
	private Account sendAccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="receive_account", nullable= false, insertable = false)
	private Account receiveAccount;
}

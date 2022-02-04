package com.savle.togethersaving.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Wish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wish_id")
	private Long wishId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="challenge_id", /*updatable = false, insertable = false,*/ nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private Challenge challenge;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", /*updatable = false, insertable = false,*/ nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
	private User hopingPerson;
}

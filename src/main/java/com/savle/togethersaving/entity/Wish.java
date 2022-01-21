package com.savle.togethersaving.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Setter
@Getter
public class Wish {

    @Id
    @GeneratedValue
    @Column(name = "wish_id")
    private Long wishId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable= false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable= false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User hopingPerson;

}

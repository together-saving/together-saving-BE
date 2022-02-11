package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;

public class WishFixture {

    public static Wish createWish( User user,Challenge challenge) {

       return Wish.builder()
                .challenge(challenge)
                .hopingPerson(user).build();
    }
}

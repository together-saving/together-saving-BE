package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Wish;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WishRepositoryTest extends RepositoryTestUtil {


    @Test
    void existsByHopingPerson_UserIdAndChallenge() {
        createUserAndChallengeSaved();
        createWish();

        wishRepository.save(wish);
        Wish wish1 = wishRepository.findById(wish.getWishId()).get();
        Assertions.assertThat(wish.getChallenge()).isEqualTo(wish1.getChallenge());
        Assertions.assertThat(wish.getHopingPerson()).isEqualTo(wish1.getHopingPerson());
        Assertions.assertThat(wishRepository.existsByHopingPerson_UserIdAndChallenge(
                user.getUserId(), previousChallenge)).isEqualTo(true);
    }
}
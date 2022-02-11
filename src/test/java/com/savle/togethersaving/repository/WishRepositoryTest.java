package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.repositoryfixture.WishFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;
import static com.savle.togethersaving.repository.repositoryfixture.WishFixture.createWish;

@DataJpaTest
class WishRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private WishRepository wishRepository;

    @Test
    void existsByHopingPerson_UserIdAndChallenge() {

        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        Wish savedWish = wishRepository.save(createWish(savedUser,savedChallenge));

        //when
        Wish findWish = wishRepository.findById(savedWish.getWishId()).get();

        //then
        Assertions.assertThat(savedWish.getChallenge()).isEqualTo(findWish.getChallenge());
        Assertions.assertThat(savedWish.getHopingPerson()).isEqualTo(findWish.getHopingPerson());
        Assertions.assertThat(wishRepository.existsByHopingPerson_UserIdAndChallenge_ChallengeId(
                savedUser.getUserId(), savedChallenge.getChallengeId())).isEqualTo(true);
    }

    @Test
    void deleteWish() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        Wish savedWish = wishRepository.save(createWish(savedUser,savedChallenge));

        //when
        wishRepository.delete(savedWish);
        Wish invalidWish = wishRepository.findById(savedWish.getWishId()).orElse(null);

        //then
        Assertions.assertThat(invalidWish).isNull();
    }

}
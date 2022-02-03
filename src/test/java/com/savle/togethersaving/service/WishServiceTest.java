package com.savle.togethersaving.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.BDDMockito.given;


class WishServiceTest extends ServiceTestUtil {


    @Mock
    private ChallengeRepository challengeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private WishRepository wishRepository;

    @InjectMocks
    private WishService wishService;


    @Test
    void addWish() {
        //given
        createUserAndChallenge();

        given(wishRepository.existsByHopingPerson_UserIdAndChallenge_ChallengeId(user.getUserId(), challenge.getChallengeId()))
                .willReturn(false);

        //when
        Wish wish = wishService.addWish(user.getUserId(), challenge.getChallengeId());
        //then
        Assertions.assertThat(wish).isNotNull();
    }

    @Test
    void addWishDuplicated() {
        //given
        createUserAndChallenge();

        given(wishRepository.existsByHopingPerson_UserIdAndChallenge_ChallengeId(user.getUserId(), challenge.getChallengeId()))
                .willReturn(true);
        //when
        boolean isExcepted = false;
        try {
            wishService.addWish(user.getUserId(), challenge.getChallengeId());
        } catch (ResponseStatusException e) {
            Assertions.assertThat(e.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
            isExcepted = true;
        }
        Assertions.assertThat(isExcepted).isTrue();
    }
}
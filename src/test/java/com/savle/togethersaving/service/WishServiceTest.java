package com.savle.togethersaving.service;

import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

        given(wishRepository.existsByHopingPerson_UserIdAndChallenge(user.getUserId(), challenge)).willReturn(true);

        //when
        wishService.addWish(user.getUserId(), challenge.getChallengeId());
        //then
        Assertions.assertThat(wishService.isWished(challenge, user.getUserId())).isEqualTo(true);
    }
}
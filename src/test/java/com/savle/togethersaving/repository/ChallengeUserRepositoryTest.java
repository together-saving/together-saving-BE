package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChallengeUserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeUserRepository challengeUserRepository;

    @Test
    void findChallengeUserByChallengeUserPk() {
        //given
        User user = User.builder()
                .email("isu@isu.com")
                .birth(LocalDate.of(1994, 01, 01))
                .gender(true)
                .password("1234")
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.png")
                .nickname("ssu")
                .role(Role.USER)
                .point(0L)
                .reward(0L)
                .build();
        User user2 = User.builder()
                .email("isu2@isu.com")
                .birth(LocalDate.of(1994, 01, 01))
                .gender(true)
                .password("1234")
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.png")
                .nickname("ssu")
                .role(Role.USER)
                .point(0L)
                .reward(0L)
                .build();

        userRepository.saveAll(Arrays.asList(user,user2));

        Challenge challenge = Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("1번챌린지 ")
                .content("챌린지 콘텐츠")
                .payment(5000L)
                .members(100L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();

        Challenge challenge2 = Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().plusDays(3L))
                .title("2번챌린지")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(100L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();
        challengeRepository.saveAll(Arrays.asList(challenge, challenge2));

/*
        ChallengeUserPK challengeUserPK = new ChallengeUserPK();
        challengeUserPK.setChallengeId(challenge.getChallengeId());
        challengeUserPK.setUserId(user.getUserId());
*/


        ChallengeUser challengeUser = ChallengeUser.builder()
                .isAutomated(false)
                .challenge(challenge)
                .user(user)
//                .challengeUserPK(challengeUserPK)
                .accumulatedBalance(0L).build();

//        challengeUserRepository.findAll();
        challengeUserRepository.save(challengeUser);

        //when
        ChallengeUser challengeUserTest =
          challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(
                challenge.getChallengeId(),user.getUserId());
//        //then
        Assertions.assertThat(challengeUserTest.getAccumulatedBalance()).isEqualTo(0L);
        Assertions.assertThat(challengeUserTest.getChallenge().getChallengeId()).isEqualTo(0L);
        Assertions.assertThat(challengeUserTest.getUser().getUserId()).isEqualTo(0L);
        Assertions.assertThat(challengeUserTest.getIsAutomated()).isFalse();
    }


}
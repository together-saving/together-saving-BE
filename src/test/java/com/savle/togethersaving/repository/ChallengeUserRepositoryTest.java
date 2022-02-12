package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture;
import com.savle.togethersaving.repository.repositoryfixture.ChallengeUserFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallengeList;
import static com.savle.togethersaving.repository.repositoryfixture.ChallengeUserFixture.*;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;
import static com.savle.togethersaving.service.servicefixture.UserFixture.user;

@DataJpaTest
public class ChallengeUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeUserRepository challengeUserRepository;

    @Test
    @DisplayName("참여중인 챌린지 조회 테스트")
    void beforeStartChallengeTest() {
        //given
        User savedUser = userRepository.save(createUser());
        List<Challenge> challengeList = challengeRepository.saveAll(createChallengeList(savedUser));
        challengeUserRepository.saveAll(createChallengeUserList(savedUser, challengeList));

        //when
        List<ChallengeUser> participatingChallenges
                = challengeUserRepository.findAllByUser_UserId(savedUser.getUserId(), PageRequest.of(
                        0, 2,Sort.by("challenge.members").descending()));

        //then
        Assertions.assertThat(participatingChallenges.size()).isEqualTo(2);
        Assertions.assertThat(participatingChallenges.get(0).getChallenge().getMembers()).isEqualTo(11L);

    }

    @Test
    void 참여여부테스트() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        challengeUserRepository.save(createChallengeUser(savedUser,savedChallenge));

        //when
        boolean isParticipated = challengeUserRepository
            .existsByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(
                    savedChallenge.getChallengeId(), savedUser.getUserId());

        //then
        Assertions.assertThat(isParticipated).isTrue();
    }

    @Test
    @DisplayName("사용자 저축금액 조회")
    void getChallengeUserSavingAmountTest() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        challengeUserRepository.save(createSavedMoneyChallengeUser(savedUser,savedChallenge));

        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(savedChallenge.getChallengeId(), savedUser.getUserId());
        Assertions.assertThat(challengeUser.getAccumulatedBalance()).isEqualTo(1000L);
    }

    @Test
    @DisplayName("사용자 저축 금액 제거")
    void removeChallengeUserSavingAmountTest() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        challengeUserRepository.save(createSavedMoneyChallengeUser(savedUser,savedChallenge));

        ChallengeUser challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(savedChallenge.getChallengeId(), savedUser.getUserId());
        challengeUser.setAccumulatedBalance(0L);
        challengeUserRepository.save(challengeUser);
        challengeUser = challengeUserRepository.findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(savedChallenge.getChallengeId(), savedUser.getUserId());

        Assertions.assertThat(challengeUser.getAccumulatedBalance()).isEqualTo(0L);
    }

}

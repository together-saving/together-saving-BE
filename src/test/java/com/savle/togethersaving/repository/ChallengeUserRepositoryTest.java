package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class ChallengeUserRepositoryTest extends RepositoryTestUtil {

    @Test
    @DisplayName("참여중인 챌린지 조회 테스트")
    void beforeStartChallengeTest() {
        //given
        createUserAndChallengeSaved();
        createChallengeUser();
        //when

        List<ChallengeUser> participatingChallenges
                = challengeUserRepository.findAllByUser(user, PageRequest.of(0, 2));

        //then
        Assertions.assertThat(participatingChallenges.size()).isEqualTo(2);

    }

    @Test
    void 참여여부테스트() {
        createUserAndChallengeSaved();
        createChallengeUser();

        boolean isParticipated = challengeUserRepository
            .existsByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(
                afterChallenge.getChallengeId(), user.getUserId());
        Assertions.assertThat(isParticipated).isTrue();
    }
}

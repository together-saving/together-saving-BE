package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeTag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class ChallengeTagRepositoryTest extends RepositoryTestUtil {

    @Test
    void findChallengeTagsByChallenge() {
        //given
        createUserAndChallengeSaved();
        createTagsAndChallengeTag();

        //when
        List<ChallengeTag> tagList = challengeTagRepository.findChallengeTagsByChallenge(previousChallenge);
        //then
        Assertions.assertThat(tagList.get(0).getTag().getName()).isEqualTo("first tag!");
        Assertions.assertThat(tagList.get(1).getTag().getName()).isEqualTo("second tag!");
    }
}
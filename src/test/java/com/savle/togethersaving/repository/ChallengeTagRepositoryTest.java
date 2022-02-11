package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.repositoryfixture.ChallengeTagFixture;
import com.savle.togethersaving.repository.repositoryfixture.TagFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.ChallengeTagFixture.createChallengeTag;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;


@DataJpaTest
class ChallengeTagRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ChallengeTagRepository challengeTagRepository;


    @Test
    void findChallengeTagsByChallenge() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        List<Tag> savedTags = tagRepository.saveAll(TagFixture.createTagsAndChallengeTag());
        challengeTagRepository.saveAll(createChallengeTag(savedChallenge,savedTags));
        //when
        List<ChallengeTag> tagList = challengeTagRepository.findChallengeTagsByChallenge(savedChallenge);
        //then
        Assertions.assertThat(tagList.get(0).getTag().getName()).isEqualTo("first tag!");
        Assertions.assertThat(tagList.get(1).getTag().getName()).isEqualTo("second tag!");
    }
}
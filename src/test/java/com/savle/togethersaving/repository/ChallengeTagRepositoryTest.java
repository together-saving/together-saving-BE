package com.savle.togethersaving.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;

@DataJpaTest
class ChallengeTagRepositoryTest {

	@Autowired
	private ChallengeTagRepository challengeTagRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private ChallengeRepository challengeRepository;

	@Test
	void findChallengeTagsByChallenge() {
		//given
		Challenge previous = Challenge.builder().challengeId(1L)
			.title("1")
			.startDate(LocalDate.now().plusDays(1)).build();
		challengeRepository.save(previous);
		Tag tag1 = Tag.builder().name("tag1").build();
		Tag tag2 = Tag.builder().name("tag2").build();
		tagRepository.save(tag1);
		tagRepository.save(tag2);
		ChallengeTag build1 = ChallengeTag.builder()
			.challenge(previous)
			.tag(tag1)
			.build();
		ChallengeTag build2 = ChallengeTag.builder()
			.challenge(previous)
			.tag(tag2)
			.build();
		challengeTagRepository.save(build1);
		challengeTagRepository.save(build2);
		//when
		List<ChallengeTag> tagList = challengeTagRepository.findChallengeTagsByChallenge(previous);
		//then
		Assertions.assertThat(tagList.get(0).getTag().getName()).isEqualTo("tag1");
		Assertions.assertThat(tagList.get(1).getTag().getName()).isEqualTo("tag2");
	}
}
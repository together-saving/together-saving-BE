package com.savle.togethersaving.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import com.savle.togethersaving.entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChallengeTagRepositoryTest {

	@Autowired
	private ChallengeTagRepository challengeTagRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private ChallengeRepository challengeRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void findChallengeTagsByChallenge() {
		//given
		User user = User.builder()
				.email("sheep@naver.com")
				.birth(LocalDate.of(2020, 01, 01))
				.gender(true)
				.password("1234")
				.phoneNumber("010-1234-5678")
				.profilePicture("http://asdasd.com")
				.nickname("NICK")
				.role(Role.USER)
				.point(0L)
				.reward(0L)
				.build();

		userRepository.save(user);

		Challenge previous = Challenge.builder()
				.host(user)
				.startDate(LocalDate.now().plusDays(2L))
				.title("돈 모으자")
				.content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
				.payment(5000L)
				.members(100L)
				.mode(Mode.FREE)
				.entryFee(5000L)
				.period(3)
				.thumbnail("http://qweqweqwe.com").build();
		challengeRepository.save(previous);

		Tag tag1 = Tag.builder().name("tagone").build();
		Tag tag2 = Tag.builder().name("tagtwo").build();
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
		Assertions.assertThat(tagList.get(0).getTag().getName()).isEqualTo("tagone");
		Assertions.assertThat(tagList.get(1).getTag().getName()).isEqualTo("tagtwo");
	}
}
package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.repository.ChallengeTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

	private final ChallengeTagRepository challengeTagRepository;

	public List<String> tagsOf(Challenge challenge) {
		return challengeTagRepository.findChallengeTagsByChallenge(challenge)
				.stream().map(c -> c.getTag().getName())
				.collect(Collectors.toList());
	}
}

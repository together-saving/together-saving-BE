package com.savle.togethersaving.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;
import com.savle.togethersaving.repository.ChallengeTagRepository;

@Service
public class TagService {

	private final ChallengeTagRepository challengeTagRepository;

	@Autowired
	public TagService(ChallengeTagRepository challengeTagRepository) {
		this.challengeTagRepository = challengeTagRepository;
	}

	public List<Tag> tagsOf(Challenge challenge) {
		return challengeTagRepository.findChallengeTagsByChallenge(challenge)
			.stream().map(column -> column.getTag())
			.collect(Collectors.toList());
	}
}
package com.savle.togethersaving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishService {

	private final WishRepository wishRepository;
	private final ChallengeRepository challengeRepository;
	private final UserRepository userRepository;

	public boolean isWished(Challenge challenge, Long userId) {
		return wishRepository.existsByHopingPerson_UserIdAndChallenge(userId, challenge);
	}

	public Wish addWish(Long userId, Long challengeId) {
		Wish wish = Wish.builder()
			.challenge(challengeRepository.getById(challengeId))
			.hopingPerson(userRepository.getById(userId)).build();
		wishRepository.save(wish);
		return wish;
	}
}

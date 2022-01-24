package com.savle.togethersaving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.repository.WishRepository;

@Service
public class WishService {

	private final WishRepository wishRepository;

	@Autowired
	public WishService(WishRepository wishRepository) {
		this.wishRepository = wishRepository;
	}

	public boolean isWished(Challenge challenge, Long userId) {
		return wishRepository.existsByHopingPerson_UserIdAndChallenge(userId, challenge);
	}
}

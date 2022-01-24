package com.savle.togethersaving.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savle.togethersaving.Data;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.ChallengeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ChallengeController {

	private final ChallengeService challengeService;
	private final UserRepository userRepository;
	private final ChallengeRepository challengeRepository;

	@Autowired
	public ChallengeController(ChallengeService challengeService,
		UserRepository userRepository, ChallengeRepository challengeRepository) {
		this.challengeService = challengeService;
		this.userRepository = userRepository;
		this.challengeRepository = challengeRepository;
	}

	@GetMapping("/v1/auth/challenges")
	public ResponseEntity<Data> getChallenges(@RequestParam String criteria,
			@RequestHeader(name = "user-id") Long userId) {

		challengeRepository.save(Challenge.builder().startDate(LocalDate.now()).title("1").build());
		userRepository.save(User.builder().email("2").birth(LocalDate.now()).gender(true).phoneNumber("2").build());

		if (criteria.equals("popularity")) {
			return new ResponseEntity<>(new Data(challengeService.getPopularChallenges(userId)), HttpStatus.OK);
		}
		return null;
	}
}

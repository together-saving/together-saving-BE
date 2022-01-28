package com.savle.togethersaving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savle.togethersaving.dto.Data;
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
	public ResponseEntity<Data> getChallenges(@RequestHeader(name = "user-id") Long userId,
			@PageableDefault(value = 7, sort = "startTime", direction = Sort.Direction.ASC) Pageable pageable) {
		return new ResponseEntity<>(new Data(challengeService.getChallenges(userId, pageable)), HttpStatus.OK);
	}
}

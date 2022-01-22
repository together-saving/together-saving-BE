package com.savle.togethersaving.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savle.togethersaving.Data;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.service.ChallengeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ChallengeController {

	private final ChallengeService challengeService;

	@Autowired
	public ChallengeController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

	@GetMapping("/v1/auth/challenges")
	public ResponseEntity<Data> getChallenges(@RequestParam String criteria) {
		if (criteria.equals("popularity")) {
			return new ResponseEntity<>(new Data(challengeService.getPopularChallenges()), HttpStatus.OK);
		}
		return null;
	}
}

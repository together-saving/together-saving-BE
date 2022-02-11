package com.savle.togethersaving.controller;


import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.service.AOPTestService;

import com.savle.togethersaving.repository.TransactionLogRepository;

import com.savle.togethersaving.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AOPTestController {

	@Autowired
	AOPTestService service;

	@Autowired
	ChallengeRepository challengeRepository;
	@GetMapping("/")
	public String methodNameTest() {
		Challenge challenge = challengeRepository.findChallengeByChallengeId(1L);

		return String.join(" ",challenge.getDays().stream().map(d -> d.getChallengeFrequencyPK().getFrequency().name()).collect(Collectors.toList()));
	}

}

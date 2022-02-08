package com.savle.togethersaving.controller;


import com.savle.togethersaving.service.AOPTestService;

import com.savle.togethersaving.repository.TransactionLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AOPTestController {

	@Autowired
	AOPTestService service;

	@GetMapping("/")
	public String methodNameTest() {
		return "Response";
	}

}

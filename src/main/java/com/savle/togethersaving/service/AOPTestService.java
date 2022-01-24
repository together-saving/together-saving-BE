package com.savle.togethersaving.service;

import org.springframework.stereotype.Service;

@Service
public class AOPTestService {

	public void aopService(int hello) {
		System.out.println("hey~");
	}
}

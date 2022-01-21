package com.savle.togethersaving;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AOPTestController {

	@GetMapping("/")
	public String methodNameTest() {
		return "Response";
	}
}

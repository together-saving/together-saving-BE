package com.savle.togethersaving.controller;

import com.savle.togethersaving.config.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.service.WishService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class WishController {

	private final WishService wishService;

	@PostMapping("/users/challenges/{challengeId}/wish")
	public ResponseEntity<HttpStatus> addWish(@PathVariable Long challengeId,
											  Authentication auth) {

		CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();

		wishService.addWish(customDetails.getUser().getUserId(), challengeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/users/challenges/{challengeId}/wish")
	public ResponseEntity<HttpStatus> deleteWish(@PathVariable Long challengeId,
												 Authentication auth) {

		CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();
		wishService.deleteWish(customDetails.getUser().getUserId(), challengeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

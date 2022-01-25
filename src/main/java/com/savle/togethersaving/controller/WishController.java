package com.savle.togethersaving.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		@RequestHeader("user-id") Long userId) {
		wishService.addWish(userId, challengeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/users/challenges/{challengeId}/wish")
	public ResponseEntity<HttpStatus> deleteWish(@PathVariable Long challengeId,
		@RequestHeader("user-id") Long userId) {
		wishService.deleteWish(userId, challengeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

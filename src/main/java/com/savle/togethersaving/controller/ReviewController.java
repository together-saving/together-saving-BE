package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.review.ResponseReviewDto;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<?> addReview(@RequestBody ReviewCreateDto review) {

        Long userId = 1L;
        reviewService.saveReview(userId, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

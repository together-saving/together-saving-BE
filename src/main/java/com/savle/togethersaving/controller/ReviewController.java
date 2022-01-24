package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.review.ReviewDto;
import com.savle.togethersaving.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Data<ReviewDto>> addReview(@RequestBody ReviewCreateDto review) {

        return  new ResponseEntity<>(new Data<>(reviewService.saveReview(review)), HttpStatus.OK);
    }

}

package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.Result;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private Result<Object> data;
    private HttpHeaders headers;
    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<Object> addReview(@RequestBody ReviewCreateDto review,
                                            @RequestHeader("X-USER-ID") Long userId) {

        createResponseMessage();
        reviewService.saveReview(userId, review);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);

    }

    private void createResponseMessage() {
        data = new Result<>();
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    }
}

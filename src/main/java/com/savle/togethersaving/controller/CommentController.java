package com.savle.togethersaving.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    @GetMapping("/users/challenges/{challengeId}/comment")
    public ResponseEntity<?> getComments(@PathVariable long challengeId, @RequestParam int offset) {
        //TODO: 서비스 호출, DTO 생성
        return null;
    }
}

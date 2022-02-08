package com.savle.togethersaving.controller;


import com.savle.togethersaving.config.security.CustomUserDetails;
import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/users/challenges/{challengeId}/comment")
    public ResponseEntity<?> getComments(@PathVariable long challengeId,
                                         @RequestParam int offset,
                                         Authentication auth) {

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        Long userId = user.getUser().getUserId();
        return new ResponseEntity<>(new Data(commentService.getComments(challengeId, userId, offset)), HttpStatus.OK);
    }
}

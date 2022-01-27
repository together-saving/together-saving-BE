package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChallengeController {

    private final ChallengeService challengeService;

    /**
     * queryParameter = page, sort
     *
     * @param userId
     * @param pageable
     * @return
     */
    @GetMapping("/auth/challenges")
    public ResponseEntity<Data> getChallenges(@RequestHeader(name = "user-id") Long userId,
                                              @PageableDefault(value = 7, sort = "startTime", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(new Data(challengeService.getChallenges(userId, pageable)), HttpStatus.OK);
    }

    @PostMapping("/challenges/{challengeId}/payment")
    public HttpEntity<?> payChallenge(@PathVariable Long challengeId) {
        Long userId = 1L;
        challengeService.payForChallenge(userId, challengeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

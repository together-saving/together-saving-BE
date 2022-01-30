package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/auth/challenges")
    public ResponseEntity<Data> getChallenges(@RequestHeader(name = "user-id") Long userId,
                                              @RequestParam String criteria,@RequestParam int page) {

        PageRequest pageable = null;
        if(criteria.equals("popularity")){
            pageable = PageRequest.of(page,7,Sort.by("members").descending());
        }else if(criteria.equals("valid")){
            pageable = PageRequest.of(page,7,Sort.by("challenge.members").descending());
        }


        return new ResponseEntity<>(new Data(challengeService.getChallenges(userId, pageable)), HttpStatus.OK);
    }

    @PostMapping("/challenges/{challengeId}/payment")
    public HttpEntity<?> changeAutoSetting(@PathVariable Long challengeId) {
        Long userId = 1L;
        challengeService.payForChallenge(userId, challengeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/challenges/{challengeId}/auto")
    public HttpEntity<?> modifyAutoSetting(@PathVariable Long challengeId) {
        Long userId = 1L;
        challengeService.changeAutoSettings(userId, challengeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

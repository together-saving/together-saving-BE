package com.savle.togethersaving.controller;

import com.savle.togethersaving.config.security.CustomUserDetails;
import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.challenge.ChallengeDetailDto;
import com.savle.togethersaving.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/auth/challenges")
    public ResponseEntity<Data> getChallenges(@RequestParam String criteria,
                                              @RequestParam int page) {


        Long userId = -1L;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            userId = ((CustomUserDetails) principal).getUser().getUserId();
        }
        PageRequest pageable = null;
        if (criteria.equals("popularity")) {
            pageable = PageRequest.of(page, 7, Sort.by("members").descending());
        } else if (criteria.equals("valid")) {
            pageable = PageRequest.of(page, 7, Sort.by("challengeId").ascending());
        } else if (criteria.equals("deadline")) {
            pageable = PageRequest.of(page, 7, Sort.by("startDate").ascending());
        }

        return new ResponseEntity<>(new Data(challengeService.getChallenges(userId, pageable)), HttpStatus.OK);
    }

    @PostMapping("/challenges/{challengeId}/payment")
    public HttpEntity<?> payChallenge(@PathVariable Long challengeId, Authentication auth) {


        CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = customDetails.getUser().getUserId();
        challengeService.payForChallenge(userId, challengeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/auth/challenges/{challengeId}")
    public HttpEntity<?> detailChallenge(@PathVariable Long challengeId) {

        Long userId = -1L;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            userId = ((CustomUserDetails) principal).getUser().getUserId();
        }

        ChallengeDetailDto detailDto = challengeService.getChallengeDetail(challengeId, userId);

        return new ResponseEntity<>(new Data(detailDto), HttpStatus.OK);
    }


    @PutMapping("/challenges/{challengeId}/auto")
    public HttpEntity<?> modifyAutoSetting(@PathVariable Long challengeId, Authentication auth) {

        CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = customDetails.getUser().getUserId();
        challengeService.changeAutoSettings(userId, challengeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.savle.togethersaving.controller;


import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {



    private final UserService userService;

    @PostMapping("/challenges/{challengeId}/saving")
    public HttpEntity<?> savingMoney(@PathVariable Long challengeId,
                                     @RequestBody CreateSavingsDto createSavingDto) {
        
        Long userId = 1L;
        userService.saveMoney(userId, challengeId, createSavingDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/challenges")
    public ResponseEntity<Data<ResponseMyChallengeDto>> retrieveMyChallenges(@RequestParam int page) {

        PageRequest pageable = PageRequest.of(page,7, Sort.by("challenge.members").descending());

        Long userId = 1L;

        return new ResponseEntity<>(new Data(userService.getMyParticipatingChallenges(userId, pageable)), HttpStatus.OK);
    }

}

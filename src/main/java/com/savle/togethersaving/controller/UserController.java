package com.savle.togethersaving.controller;


import com.savle.togethersaving.config.security.CustomUserDetails;
import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseMyChallengeDto;
import com.savle.togethersaving.dto.user.SignUpDto;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.service.ReviewService;
import com.savle.togethersaving.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final ReviewService reviewService;

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("users/challenges/{challengeId}/saving")
    public HttpEntity<?> savingMoney(@PathVariable Long challengeId,
                                     @RequestBody CreateSavingsDto createSavingDto,
                                     Authentication auth) {

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        userService.saveMoney(user.getUser().getUserId(), challengeId, createSavingDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/users/my-challenges")
    public ResponseEntity<Data<ResponseMyChallengeDto>> retrieveMyChallenges(@RequestParam int page,
                                                                             Authentication auth) {

        PageRequest pageable = PageRequest.of(page, 7, Sort.by("challenge.members").descending());

        CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = customDetails.getUser().getUserId();

        return new ResponseEntity<>(new Data(userService.getMyParticipatingChallenges(userId, pageable)), HttpStatus.OK);

    }

    @PostMapping("/users/reviews")
    public ResponseEntity<?> addReview(@RequestBody ReviewCreateDto review, Authentication auth) {

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        reviewService.saveReview(user.getUser().getUserId(), review);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PostMapping("auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto dto) {

        try {

            User user = User.builder()
                    .email(dto.getEmail())
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .birth(LocalDate.of(2022, 01, 27))
                    .gender(true)
                    .phoneNumber("010-1234-567822")
                    .profilePicture("http:qwer.com22")
                    .nickname("testNick22!")
                    .role(dto.getRole())
                    .point(0L)
                    .reward(0L)
                    .build();

            userService.createUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}

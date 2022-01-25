package com.savle.togethersaving.controller;


import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.dto.user.ResponseSavingsDto;
import com.savle.togethersaving.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/challenges/{challengeId}/saving")
    public ResponseEntity<Data<ResponseSavingsDto>> savingMoney(@PathVariable Long challengeId,
                                                                @RequestBody CreateSavingsDto createSavingDto) {

        Long userId = 1L;

        return  new ResponseEntity<>(new Data<>(userService.saveMoney(userId,challengeId,createSavingDto)), HttpStatus.OK);
    }
}

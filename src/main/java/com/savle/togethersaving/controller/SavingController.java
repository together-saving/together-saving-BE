package com.savle.togethersaving.controller;

import com.savle.togethersaving.config.security.CustomUserDetails;
import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.service.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SavingController {
    private final SavingService savingService;

    @GetMapping("/users/challenges/{challengeId}/saving-histories")
    public ResponseEntity<Data<SavingStatusDto>> getSavingStatus(@PathVariable Long challengeId,
                                                                 Authentication auth) {
        CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = customDetails.getUser().getUserId();

        return new ResponseEntity<>(new Data(savingService.getSavingStatus(userId,challengeId)), HttpStatus.OK);
    }

}

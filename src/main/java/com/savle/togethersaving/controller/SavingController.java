package com.savle.togethersaving.controller;

import com.savle.togethersaving.config.security.CustomUserDetails;
import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.service.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                                                                 @RequestParam String period,
                                                                 @RequestParam String ordering,
                                                                 Authentication auth) {
        CustomUserDetails customDetails = (CustomUserDetails) auth.getPrincipal();
        Long userId = customDetails.getUser().getUserId();
        PageRequest pageRequest = null;
        if(ordering.equals("desc")) {
            pageRequest = PageRequest.of(0, 1000, Sort.by("created_at").descending());
        } else if (ordering.equals("asc")) {
            pageRequest = PageRequest.of(0, 1000, Sort.by("created_at").ascending());
        }

        return new ResponseEntity<>(new Data(savingService.getSavingStatus(userId,challengeId,period,pageRequest)), HttpStatus.OK);
    }

}

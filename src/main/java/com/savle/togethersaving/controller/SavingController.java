package com.savle.togethersaving.controller;

import com.savle.togethersaving.dto.Data;
import com.savle.togethersaving.dto.saving.SavingStatusDto;
import com.savle.togethersaving.service.SavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SavingController {
    private final SavingService savingService;

    @GetMapping("/users/challenges/{challengeId}/saving-histories")
    public ResponseEntity<Data<SavingStatusDto>> getSavingStatus(@PathVariable Long challengeId,
                                                                 @RequestParam Integer period,
                                                                 @RequestHeader(name = "user-id") Long userId,
                                                                 @RequestParam String ordering) {
        savingService.getSavingStatus(userId,challengeId);
        return null;
    }

}

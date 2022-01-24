package com.savle.togethersaving.service;

import com.savle.togethersaving.dto.AllChallengeDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;


    public Challenge getChallengeByChallengeId(Long challengeId){

        return challengeRepository.getById(challengeId);
    }

//    public List<AllChallengeDto> getAllChallenges(Long userId) {
//
//
//        if (userId != null) {
//
//
//            boolean isWish = wishRepository.findById(userId);
//            List<Challenge> challenges = challengeRepository.findAllChallengesHaveNotStarted(LocalDateTime.now());
//
//            return challenges.stream().map(c -> AllChallengeDto.fromEntity(c, true)
//
//            ).collect(Collectors.toList());
//        }
//
//    }
    }

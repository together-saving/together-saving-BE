package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ChallengeUserService {


    private final ChallengeUserRepository challengeUserRepository;

    public List<ChallengeUser> getChallengeUser(User user, Pageable pageable) {

        // 유저를 찾는다.
        return challengeUserRepository.findAllByUser(user, pageable);
    }
}
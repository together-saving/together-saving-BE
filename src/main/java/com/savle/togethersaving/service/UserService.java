package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUserId(Long userId){

        return userRepository.getById(userId);
    }
}
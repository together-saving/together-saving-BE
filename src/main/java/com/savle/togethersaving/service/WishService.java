package com.savle.togethersaving.service;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.UserRepository;
import com.savle.togethersaving.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {


    private final WishRepository wishRepository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public boolean isWished(Challenge challenge, Long userId) {
        return wishRepository.existsByHopingPerson_UserIdAndChallenge_ChallengeId(userId, challenge.getChallengeId());
    }

    @Transactional
    public Wish addWish(Long userId, Long challengeId) {
        throwExistsWishException(userId, challengeId);
        Wish wish = Wish.builder()
                .challenge(challengeRepository.getByChallengeId(challengeId))
                .hopingPerson(userRepository.getUserByUserId(userId)).build();
        wishRepository.save(wish);
        return wish;
    }

    private void throwExistsWishException(Long userId, Long challengeId) {
        if (wishRepository.existsByHopingPerson_UserIdAndChallenge_ChallengeId(userId, challengeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicated Wish. user-id :" + userId + ", challenge-id : " + challengeId);
        }
    }

    @Transactional
    public void deleteWish(Long userId, Long challengeId) {
        Wish wish = wishRepository.findWishByChallenge_ChallengeIdAndHopingPerson_UserId(challengeId, userId)
                .orElseThrow();
        wishRepository.delete(wish);
    }

}

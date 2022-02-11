package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.ChallengeUserPK;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, ChallengeUserPK> {


    List<ChallengeUser> findAllByUser_UserId(Long userId, Pageable pageable);
    ChallengeUser findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);

    List<ChallengeUser> findAllByChallenge_ChallengeId (Long challengeId);

    @Query(value = "select cu from ChallengeUser cu join fetch cu.challenge join fetch cu.user where cu.challenge.challengeId=:challengeId ")
    List<ChallengeUser> findAllByChallenge_ChallengeIdfetch (Long challengeId);

    boolean existsByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);
}

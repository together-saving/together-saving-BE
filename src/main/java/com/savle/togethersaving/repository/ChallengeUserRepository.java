package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.ChallengeUserPK;
import com.savle.togethersaving.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, ChallengeUserPK> {


    List<ChallengeUser> findAllByUser_UserId(Long userId, Pageable pageable);
    ChallengeUser findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);

    List<ChallengeUser> findAllByChallenge_ChallengeId (Long ChallengeId);

    boolean existsByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);
}

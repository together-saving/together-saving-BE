package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.ChallengeUserPK;
import com.savle.togethersaving.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, ChallengeUserPK> {


    List<ChallengeUser> findAllByUser_UserId(Long userId, Pageable pageable);

    ChallengeUser findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);
    boolean existsByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);
}

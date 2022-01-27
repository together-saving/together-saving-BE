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

    // select cu.challenge_id
    //from challenge_user cu
    //    inner join challenge c
    //    on c.challenge_id = cu.challenge_id
    //where cu.user_id = 1
    //order by (c.start_date)
//    @Query(nativeQuery = true "")
    List<ChallengeUser> findAllByUser(User user, Pageable pageable);

    ChallengeUser findByChallengeUserPK_ChallengeIdAndChallengeUserPK_UserId(Long challengeId, Long userId);
}

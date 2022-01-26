package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.ChallengeUserPK;
import com.savle.togethersaving.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, ChallengeUserPK> {

    List<ChallengeUser> findAllByUser(User user, Pageable pageable);
}

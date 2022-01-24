package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {


//    @Query("select c from Challenge c join fetch c.tagList where c.startDate >= : currentDateTime")
//    List<Challenge> findAllChallengesHaveNotStarted(LocalDateTime currentDateTime);

//    @Query("select new com.savle.togethersaving.dto.AllChallengeDto(c.id) " +
//            "from Challenge c join c.tagList ")
//
//    List<Challenge> findAllChallengesHaveNotStarted(LocalDateTime currentDateTime);
}

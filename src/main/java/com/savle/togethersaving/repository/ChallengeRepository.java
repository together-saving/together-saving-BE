package com.savle.togethersaving.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.savle.togethersaving.entity.Challenge;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

}

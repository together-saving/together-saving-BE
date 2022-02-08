package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.ChallengeFrequencyPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequencyRepository extends JpaRepository<ChallengeFrequency, ChallengeFrequencyPK> {
}
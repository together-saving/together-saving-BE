package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.Frequency;
import com.savle.togethersaving.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFixture.createChallenge;
import static com.savle.togethersaving.repository.repositoryfixture.ChallengeFrequencyFixture.createChallengeFrequency;
import static com.savle.togethersaving.repository.repositoryfixture.UserFixture.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ChallengeFrequencyRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeFrequencyRepository challengeFrequencyRepository;

    @Test
    void findAllByChallengeFrequencyPK_ChallengeId() {
        //given
        User savedUser = userRepository.save(createUser());
        Challenge savedChallenge = challengeRepository.save(createChallenge(savedUser));
        challengeFrequencyRepository.saveAll(createChallengeFrequency(savedChallenge));

        //when
        List<ChallengeFrequency> frequencies = challengeFrequencyRepository
                .findAllByChallengeFrequencyPK_ChallengeId(savedChallenge.getChallengeId());

        //when
        assertEquals(frequencies.get(0).getChallengeFrequencyPK().getFrequency(), Frequency.MON);
        assertEquals(frequencies.get(1).getChallengeFrequencyPK().getFrequency(), Frequency.TUE);
        assertEquals(frequencies.get(2).getChallengeFrequencyPK().getFrequency(), Frequency.WED);
    }
}
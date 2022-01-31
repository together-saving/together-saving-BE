package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.Frequency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeFrequencyRepositoryTest extends RepositoryTestUtil{

    @Test
    void findAllByChallengeFrequencyPK_ChallengeId() {
        createUserAndChallengeSaved();
        setFrequency();
        List<ChallengeFrequency> frequencies = challengeFrequencyRepository
                .findAllByChallengeFrequencyPK_ChallengeId(previousChallenge.getChallengeId());
        assertEquals(frequencies.get(0).getChallengeFrequencyPK().getFrequency(), Frequency.MON);
        assertEquals(frequencies.get(1).getChallengeFrequencyPK().getFrequency(), Frequency.TUE);
        assertEquals(frequencies.get(2).getChallengeFrequencyPK().getFrequency(), Frequency.WED);
    }
}
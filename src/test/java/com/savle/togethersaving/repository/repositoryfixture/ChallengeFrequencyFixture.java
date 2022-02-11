package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChallengeFrequencyFixture {

    public static List<ChallengeFrequency> createChallengeFrequency(Challenge challenge){

        List<ChallengeFrequency> challengeFrequencyList = new ArrayList<>();

        ChallengeFrequency fre1 = ChallengeFrequency.builder()
                .challenge(challenge).build();
        fre1.getChallengeFrequencyPK().setFrequency(Frequency.MON);

        ChallengeFrequency fre2 = ChallengeFrequency.builder()
                .challenge(challenge).build();
        fre2.getChallengeFrequencyPK().setFrequency(Frequency.TUE);

        ChallengeFrequency fre3 = ChallengeFrequency.builder()
                .challenge(challenge).build();
        fre3.getChallengeFrequencyPK().setFrequency(Frequency.WED);

        challengeFrequencyList.add(fre1);
        challengeFrequencyList.add(fre2);
        challengeFrequencyList.add(fre3);

        return challengeFrequencyList;
    }
}

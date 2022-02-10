package com.savle.togethersaving.service;


import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.entity.*;
import com.savle.togethersaving.repository.ChallengeFrequencyRepository;
import com.savle.togethersaving.repository.ChallengeRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SchdulerTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private ChallengeFrequencyRepository challengeFrequencyRepository;
    @Autowired
    private ChallengeUserRepository challengeUserRepository;

    @Autowired
    private ChallengeCountService challengeCountService;
    @Test
    @Transactional
    public void test(){
        int targetDay = LocalDate.now().minusDays(1).getDayOfWeek().getValue(); //어제 일자로 계산.
        final Frequency[] week = {Frequency.SUN ,Frequency.MON, Frequency.TUE, Frequency.WED,Frequency.THU, Frequency.FRI, Frequency.SAT};
        List<ChallengeFrequency> cf =  challengeFrequencyRepository.findChallengeFrequencyListByDay(week[targetDay]);

        List<List<ChallengeUser>> cus = cf.stream().
                filter(challengeFrequency -> challengeFrequency.getChallenge().getIsActive())
                .map(challengeFrequency -> {
                    challengeCountService.caculateCount(challengeFrequency);
                    return challengeUserRepository.findAllByChallenge_ChallengeIdfetch(challengeFrequency.getChallenge().getChallengeId()); }
                ).collect(Collectors.toList());

        cus.stream().flatMap(Collection::stream).filter(ChallengeUser::getIsAutomated).forEach(challengeUser -> {
            CreateSavingsDto csd = CreateSavingsDto.builder().savingAmount(challengeUser.getChallenge().getPayment()).build();
            userService.saveMoney(challengeUser.getUser().getUserId(),challengeUser.getChallenge().getChallengeId(),csd);
        });
    }
}

package com.savle.togethersaving.service.scheduler;

import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.entity.ChallengeFrequency;
import com.savle.togethersaving.entity.ChallengeUser;
import com.savle.togethersaving.entity.Frequency;
import com.savle.togethersaving.repository.ChallengeFrequencyRepository;
import com.savle.togethersaving.repository.ChallengeUserRepository;
import com.savle.togethersaving.service.ChallengeCountService;
import com.savle.togethersaving.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SavingSchduler {

    private final ChallengeFrequencyRepository challengeFrequencyRepository;
    private final ChallengeUserRepository challengeUserRepository;
    private final ChallengeCountService challengeCountService;
    private final UserService userService;
    @Scheduled(cron = "0 1 0 * * * ")
    public void automateSaving(){

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

package com.savle.togethersaving.service.scheduler;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.repository.ChallengeFrequencyRepository;
import com.savle.togethersaving.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;


// 1. 자동 저축한 사람들 돈 걷기.
// 2. 챌린지 끝났을 때 처리..
// 3. frequency 검색해서
@RequiredArgsConstructor
@Service
public class SchedulerTest {

    private final ChallengeRepository challengeRepository;
    private final ChallengeFrequencyRepository challengeFrequencyRepository;

    @Scheduled(cron = "0/5 * * * * *")
    public void test(){
        DayOfWeek targetDay = LocalDate.now().minusDays(1).getDayOfWeek(); //어제 일자로 계산.
        // 챌린지의 요일들을 가져왔음.
        // 챌린지 days << 쏙

    }
}

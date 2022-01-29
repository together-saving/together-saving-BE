package com.savle.togethersaving.repository;

import com.savle.togethersaving.dto.review.ReviewCreateDto;
import com.savle.togethersaving.dto.user.CreateSavingsDto;
import com.savle.togethersaving.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTestUtil {

    @Autowired
    protected ChallengeRepository challengeRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ReviewRepository reviewRepository;
    @Autowired
    protected TagRepository tagRepository;
    @Autowired
    protected ChallengeTagRepository challengeTagRepository;
    @Autowired
    protected WishRepository wishRepository;
    @Autowired
    protected ChallengeUserRepository challengeUserRepository;

    protected User user;
    protected Challenge previousChallenge;
    protected Challenge afterChallenge;
    protected Challenge biggest;
    protected Challenge fourChallenge;
    protected Challenge fiveChallenge;
    protected Challenge sixChallenge;
    protected Challenge sevenChallenge;
    protected Challenge eightChallenge;
    protected Challenge nineChallenge;

    protected ChallengeUser challengeUser1;
    protected ChallengeUser challengeUser2;
    protected ChallengeUser challengeUser3;


    protected Tag firstTag;
    protected Tag secondTag;
    protected ChallengeTag firstChallengeTag;
    protected ChallengeTag secondChallengeTag;

    protected Wish wish;

    protected Account receiveAccount;
    protected Account sendAccount;

    protected ReviewCreateDto reviewCreateDto;
    protected CreateSavingsDto createSavingDto;

    void createUserAndChallengeSaved() {

        user = User.builder()
                .email("sheep@naver.com")
                .birth(LocalDate.of(2020, 01, 01))
                .gender(true)
                .password("1234")
                .phoneNumber("010-1234-5678")
                .profilePicture("http://asdasd.com")
                .nickname("NICK")
                .role("user")
                .point(0L)
                .reward(0L)
                .build();


        previousChallenge = Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(11L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();

        afterChallenge = Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().minusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지였다")
                .payment(15000L)
                .members(13L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();

        biggest = Challenge.builder()
                .host(user)
                .startDate(LocalDate.now().plusDays(2L))
                .title("돈 모으자")
                .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
                .payment(5000L)
                .members(15L)
                .mode(Mode.FREE)
                .entryFee(5000L)
                .period(3)
                .thumbnail("http://qweqweqwe.com").build();


        userRepository.save(user);
        challengeRepository.save(previousChallenge);
        challengeRepository.save(afterChallenge);
        challengeRepository.save(biggest);
        Arrays.asList(fourChallenge,fiveChallenge,sixChallenge,sevenChallenge,eightChallenge, nineChallenge)
            .stream().forEach(challenge -> {
                challenge = buildChallenge();
                challengeRepository.save(challenge);
        });
    }

    private Challenge buildChallenge() {
        return Challenge.builder()
            .host(user)
            .startDate(LocalDate.now().plusDays(2L))
            .title("돈 모으자")
            .content("이 챌린지는 담배를 아껴서 돈 모으는 챌린지다")
            .payment(5000L)
            .members(13L)
            .mode(Mode.FREE)
            .entryFee(5000L)
            .period(3)
            .thumbnail("http://qweqweqwe.com").build();
    }

    void createTagsAndChallengeTag() {
        firstTag = Tag.builder().name("first tag!").build();
        secondTag = Tag.builder().name("second tag!").build();

        firstChallengeTag = ChallengeTag.builder()
                .challenge(previousChallenge)
                .tag(firstTag)
                .build();
        secondChallengeTag = ChallengeTag.builder()
                .challenge(previousChallenge)
                .tag(secondTag)
                .build();

        tagRepository.save(firstTag);
        tagRepository.save(secondTag);
        challengeTagRepository.save(firstChallengeTag);
        challengeTagRepository.save(secondChallengeTag);
    }

    void createWish(){
        wish = Wish.builder()
                .challenge(previousChallenge)
                .hopingPerson(user).build();

        wishRepository.save(wish);
    }

    void createChallengeUser(){

        challengeUser1 = ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(1L,user.getUserId()))
                .accumulatedBalance(0L)
                .isAutomated(false)
                .challenge(afterChallenge)
                .user(user)
                .build();
        challengeUser2 = ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(2L,user.getUserId()))
                .accumulatedBalance(0L)
                .isAutomated(false)
                .challenge(previousChallenge)
                .user(user)
                .build();
        challengeUser3 = ChallengeUser.builder()
                .challengeUserPK(new ChallengeUserPK(3L,user.getUserId()))
                .accumulatedBalance(0L)
                .isAutomated(true)
                .challenge(biggest)
                .user(user)
                .build();

        challengeUserRepository.save(challengeUser1);
        challengeUserRepository.save(challengeUser2);
        challengeUserRepository.save(challengeUser3);
    }
}

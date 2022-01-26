package com.savle.togethersaving.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.savle.togethersaving.dto.PopularChallengeDto;
import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {

	private final ChallengeRepository challengeRepository;
	private final TagService tagService;
	private final WishService wishService;
	private final UserService userService;

	public List<PopularChallengeDto> getChallenges(Long userId, Pageable pageable) {
		List<Challenge> challengeList = challengeRepository
			.findChallengesByStartDateGreaterThan(LocalDate.now(), pageable);
		return challengeList.stream()
			.map(challenge -> mapToPopularDto(challenge, userId))
			.collect(Collectors.toList());
	}

	private PopularChallengeDto mapToPopularDto(Challenge challenge, Long userId) {
		PopularChallengeDto dto = PopularChallengeDto.challengeOf(challenge);
		dto.setTags(tagService.tagsOf(challenge));
		dto.setWished(wishService.isWished(challenge, userId));
		return dto;
	}

	public void payForChallenge(Long userId,Long challengeId){
		User user = userService.getUserByUserId(userId);
		Challenge challenge = getChallengeByChallengeId(challengeId);

		Long entryFee = challenge.getEntryFee();

	}

	public Challenge getChallengeByChallengeId(Long challengeId) {

    	return challengeRepository.getById(challengeId);
  	}
}
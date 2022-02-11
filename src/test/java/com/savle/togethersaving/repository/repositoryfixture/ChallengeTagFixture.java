package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Challenge;
import com.savle.togethersaving.entity.ChallengeTag;
import com.savle.togethersaving.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class ChallengeTagFixture {

    public static List<ChallengeTag> createChallengeTag(Challenge challenge, List<Tag> tags) {

        List<ChallengeTag> challengeTagList = new ArrayList<>();
        ChallengeTag firstChallengeTag = ChallengeTag.builder()
                .challenge(challenge)
                .tag(tags.get(0))
                .build();
        ChallengeTag  secondChallengeTag = ChallengeTag.builder()
                .challenge(challenge)
                .tag(tags.get(1))
                .build();

        challengeTagList.add(firstChallengeTag);
        challengeTagList.add(secondChallengeTag);

        return challengeTagList;
    }
}

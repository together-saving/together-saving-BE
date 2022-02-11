package com.savle.togethersaving.repository.repositoryfixture;

import com.savle.togethersaving.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagFixture {

    public static List<Tag> createTagsAndChallengeTag() {
        List<Tag> tags = new ArrayList<>();
        Tag firstTag = Tag.builder().name("first tag!").build();
        Tag secondTag = Tag.builder().name("second tag!").build();
        tags.add(firstTag);
        tags.add(secondTag);
        return tags;
    }
}

package com.savle.togethersaving.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.savle.togethersaving.entity.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, String> {
}

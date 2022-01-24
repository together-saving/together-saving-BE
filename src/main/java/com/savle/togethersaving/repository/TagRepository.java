package com.savle.togethersaving.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.savle.togethersaving.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {
}

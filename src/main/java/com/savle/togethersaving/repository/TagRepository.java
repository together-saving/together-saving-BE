package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}

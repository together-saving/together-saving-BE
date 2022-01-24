package com.savle.togethersaving.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.savle.togethersaving.entity.User;
import com.savle.togethersaving.entity.Wish;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}

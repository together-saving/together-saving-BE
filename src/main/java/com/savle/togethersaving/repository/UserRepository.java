package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

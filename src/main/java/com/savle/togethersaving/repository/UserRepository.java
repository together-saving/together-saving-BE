package com.savle.togethersaving.repository;

import com.savle.togethersaving.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    Boolean existsByEmail(String email);

    User getUserByUserId(Long userId);
    User getUserByRole(String role);

}

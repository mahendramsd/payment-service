package com.daofab.service.repository;

import com.daofab.service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mahendra on 5/18/2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndAccessToken(String username, String hashToken);
}

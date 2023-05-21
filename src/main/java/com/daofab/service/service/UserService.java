package com.daofab.service.service;

import com.daofab.service.domain.User;
import com.daofab.service.dto.request.LoginRequest;
import com.daofab.service.dto.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

/**
 * @author Mahendra on 5/18/2023
 */
public interface UserService extends UserDetailsService {
    Optional<User> findByUser(String username, String hashToken);

    LoginResponse loginUser(LoginRequest loginRequest);
}

package com.daofab.service.service.impl;

import com.daofab.service.config.SecurityConfig;
import com.daofab.service.domain.User;
import com.daofab.service.dto.request.LoginRequest;
import com.daofab.service.dto.response.LoginResponse;
import com.daofab.service.exception.CustomException;
import com.daofab.service.repository.UserRepository;
import com.daofab.service.service.UserService;
import com.daofab.service.util.CustomErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Mahendra on 5/18/2023
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;



    public UserServiceImpl(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public Optional<User> findByUser(String username, String hashToken) {
        return userRepository.findByUsernameAndAccessToken(username,hashToken);
    }

    /**
     * Login User
     * @param loginRequest
     * @return
     */
    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        logger.debug("User Authenticated {}", loginRequest.getUsername());
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new CustomException(CustomErrorCodes.USER_NOT_FOUND));
        String token = securityConfig.generateToken(user.getUsername());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setUsername(user.getUsername());
        loginResponse.setAccessToken(token);
        user.setAccessToken(token);
        userRepository.save(user);
        return loginResponse;
    }


    /**
     * Authenticate
     * @param username
     * @param password
     * @throws Exception
     */
    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new CustomException(CustomErrorCodes.USER_DISABLED);
        } catch (BadCredentialsException e) {
            throw new CustomException(CustomErrorCodes.INVALID_CREDENTIALS);
        }
    }

    /**
     * loadUserByUsername
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
        }
        throw new CustomException(CustomErrorCodes.USER_NOT_FOUND);
    }
}

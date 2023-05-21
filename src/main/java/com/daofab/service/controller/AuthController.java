package com.daofab.service.controller;

import com.daofab.service.dto.ResponseDto;
import com.daofab.service.dto.request.LoginRequest;
import com.daofab.service.dto.response.LoginResponse;
import com.daofab.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahendra on 5/18/2023
 */
@RestController
@Api(value = "Auth Controller")
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "Payment Service Login", response = LoginResponse.class)
    public ResponseEntity<ResponseDto> authenticateUser(@RequestBody LoginRequest loginRequest) {
        logger.info("Login endpoint called {}", loginRequest.getUsername());
        ResponseDto responseDto = ResponseDto.success(userService.loginUser(loginRequest));
        return ResponseEntity.ok(responseDto);
    }
}

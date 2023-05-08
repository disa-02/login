package com.login.login.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.login.login.entities.User;
import com.login.login.request.UserLoggingRequest;
import com.login.login.services.UserService;
import com.login.login.utils.JWTUtil;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    
    @Autowired
    UserService userService;

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody UserLoggingRequest userLoggingRequest) {
        Optional<User> user = userService.getUserByCredentials(userLoggingRequest.getEmail(), userLoggingRequest.getPassword()); 
        if( !user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        String token = JWTUtil.generateToken(user.get());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    
}

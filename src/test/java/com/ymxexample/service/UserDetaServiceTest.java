package com.ymxexample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetaServiceTest {
@Autowired
private UserDetailsService userDetailsService;
    @Test
    void loadUserByUsername() {

        UserDetails userDetails=userDetailsService.loadUserByUsername("wukong");
        System.out.println("userDetails = "+userDetails);
    }
}
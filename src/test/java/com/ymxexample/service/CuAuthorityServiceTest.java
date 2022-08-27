package com.ymxexample.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CuAuthorityServiceTest {
@Autowired
private CuAuthorityService cuAuthorityService;


    @Test
    void addCuAuthority() {
        Integer i= cuAuthorityService.addCuAuthority(1,5);
        System.out.println("i="+i);

    }
}
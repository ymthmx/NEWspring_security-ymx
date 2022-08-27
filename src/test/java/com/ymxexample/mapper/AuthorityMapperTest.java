package com.ymxexample.mapper;

import com.ymxexample.domain.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorityMapperTest {
@Autowired
private AuthorityMapper authorityMapper;
    @Test
    void getAuthorityListByUsername() {
        List<Authority> authorityList =
                authorityMapper.getAuthorityListByUsername("678");
        System.out.println("authorityList = " + authorityList);
    }
}
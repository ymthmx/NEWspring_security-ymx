package com.ymxexample.mapper;

import com.ymxexample.domain.CuAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CuAuthorityMapperTest {
@Autowired
private CuAuthorityMapper cuAuthorityMapper;
    @Test
    void addCuAuthority() {

//        CuAuthority cuAuthority=new CuAuthority();
//        cuAuthority.setAuthorityId(2);
//        cuAuthority.setCustomerId(11);
        Integer i= cuAuthorityMapper.addCuAuthority(2,10);
        System.out.println("添加的结果为：i = " + i);

    }
}
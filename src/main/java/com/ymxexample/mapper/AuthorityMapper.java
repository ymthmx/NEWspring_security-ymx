package com.ymxexample.mapper;

import com.ymxexample.domain.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    /**
     * 根据用户账号查询用户权限信息
     * @param username 用户账号
     * @return 用户权限列表
     */



    List<Authority> getAuthorityListByUsername(@Param("username")String username);

}

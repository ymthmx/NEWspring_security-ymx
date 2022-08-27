package com.ymxexample.service;

import com.ymxexample.domain.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//针对Authority的service层
public interface AuthorityService {

    /**
     * 根据用户账号查询对应的权限列表
     *
     * @param username
     * @return 权限列表
     */

    List<Authority> getAuthorityListByUsername(String username);
}

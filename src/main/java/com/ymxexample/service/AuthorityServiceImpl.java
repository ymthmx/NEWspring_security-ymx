package com.ymxexample.service;

import com.ymxexample.domain.Authority;
import com.ymxexample.mapper.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    private AuthorityMapper authorityMapper;
    /**
     * 根据用户账号查询对应的权限列表
     *
     * @param username
     * @return 权限列表
     */
    @Override
    public List<Authority> getAuthorityListByUsername(String username) {
        return authorityMapper.getAuthorityListByUsername(username);
    }
}

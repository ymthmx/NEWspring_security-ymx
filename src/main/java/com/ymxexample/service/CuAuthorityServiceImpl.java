package com.ymxexample.service;


import com.ymxexample.domain.CuAuthority;
import com.ymxexample.mapper.CuAuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CuAuthorityServiceImpl implements CuAuthorityService{

    @Autowired
    private CuAuthorityMapper cuAuthorityMapper;


    /**
     * 注册后添加VIP权限
     *
     * @param authorityId
     * @param customerId
     * @return
     */
    @Override
    public Integer addCuAuthority(Integer authorityId, Integer customerId) {
        return cuAuthorityMapper.addCuAuthority(authorityId,customerId);
    }
}

package com.ymxexample.service;



public interface CuAuthorityService {

    /**
     *  注册后添加VIP权限
     * @param authorityId
     * @param customerId
     * @return
     */


    Integer addCuAuthority(Integer authorityId,Integer customerId);

}

package com.ymxexample.mapper;

import com.ymxexample.domain.CuAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CuAuthorityMapper {

    //注册账号时,添加VIP权限

    Integer addCuAuthority(@Param("authorityId")Integer authorityId,@Param("customerId")Integer customerId);

}

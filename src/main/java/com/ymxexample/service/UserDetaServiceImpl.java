package com.ymxexample.service;

import com.ymxexample.domain.Authority;
import com.ymxexample.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetaServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthorityService authorityService;

    /**
     * 使用spring security框架,实现根据用户账号,查询用户信息
     * @param username  用户账号
     * @return UserDetails  用户详情
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=customerService.getCustomerByUsername(username);
        List<Authority> authorityList=authorityService.getAuthorityListByUsername(username);

        //对权限进行类型转换:  --->List<Authority>  -->  List<SimpleGrantedAuthority>
        List<SimpleGrantedAuthority> simpleGrantedAuthorities=
                authorityList
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                        .collect(Collectors.toList());

        //返回封装的UserDetails用户详情类
        if(customer!=null){   //数据库中存在该用户
            UserDetails userDetails=
                    new User(customer.getUsername(),customer.getPassword(),simpleGrantedAuthorities);
            return userDetails;
        }else {  //不存在该用户
            throw new UsernameNotFoundException("当前用户账号不存在");
        }
//        return null;
    }
}

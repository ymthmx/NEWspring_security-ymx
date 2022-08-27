package com.ymxexample.controller;

import com.ymxexample.domain.Customer;
import com.ymxexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserInfoController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/user1")
    @ResponseBody
    public String getUserInfo(){
        //获取上下文
        SecurityContext context = SecurityContextHolder.getContext();
        //获取用户相关信息
        Authentication authentication = context.getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        String username=String.valueOf(userDetails.getUsername());
//        List<Customer> customerList = customerService.getCustomerListByUsername(username);
        // model 对象理解成 临时的存储空间
//        model.addAttribute("customerList",customerList);

//        System.out.println(customerList);
        System.out.println("username: "+userDetails.getUsername());
        return "登录的用户名为: " + userDetails.getUsername();
//        return "数据:"+customerList;
    }
}

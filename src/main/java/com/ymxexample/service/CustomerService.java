package com.ymxexample.service;


import com.ymxexample.domain.Customer;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CustomerService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return Customer
     */
    Customer getCustomerByUsername(String username);


    /**
     * 根据用户名查询id
     * @param username
     * @return
     */

    Integer getIdByUsername(String username);




    /**
     *  向数据库添加用户账号和密码
     * @param username  账号
     * @param password  密码
     * @return
     */
    Integer addCustomer(String username, String password);


    /**
     * 根据用户名查询用户信息
     * @param
     * @return
     */

    List<Customer> getCustomerListByUsername(String username);


    /**
     * 根据用户名修改用户名
     * @param newusername
     * @param oldusername
     * @return
     */

    Integer updateUsernameByUsername(String newusername,String oldusername);



    /**
     * 通过用户名查询用户密码
     * @param username
     * @return
     */
    String queryPasswordByUsername(String username);

    /**
     * 根据密码改密码
     * @param newpassword
     * @param username
     * @return
     */
    Integer updatePasswordByUsername(String newpassword,String username );




    /**
     * 获取所有用户信息
     * @return
     */
    List<Customer> getAllCustomer(Integer page);



    /**
     * 查询用户表中有多少条数据
     * @return
     */
    Integer queryCustomerNum();
    }


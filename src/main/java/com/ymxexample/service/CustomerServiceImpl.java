package com.ymxexample.service;


import com.ymxexample.domain.Customer;
import com.ymxexample.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return Customer
     */
    @Override
    public Customer getCustomerByUsername(String username) {

        return customerMapper.getCustomerByUsername(username);
    }

    /**
     * 根据用户名查询id
     *
     * @param username
     * @return
     */
    @Override
    public Integer getIdByUsername(String username) {
        return customerMapper.getIdByUsername(username);
    }


    /**
     * 向数据库添加用户账号和密码
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @Override
    public Integer addCustomer(String username, String password) {
        return customerMapper.insertCustomer(username, password);

    }



    /**
     * 根据用户名查询用户信息
     *
     * @param
     * @return
     */
    @Override
    public List<Customer> getCustomerListByUsername(String username) {
        return customerMapper.getCustomerListByUsername(username);
    }

    /**
     * 根据用户名修改用户名
     *
     * @param newusername
     * @param oldusername
     * @return
     */
    @Override
    public Integer updateUsernameByUsername(String newusername, String oldusername) {
        return customerMapper.updateUsernameByUsername(newusername,oldusername);
    }

    /**
     * 通过用户名查询用户密码
     *
     * @param username
     * @return
     */
    @Override
    public String queryPasswordByUsername(String username) {
        return customerMapper.queryPasswordByUsername(username);
    }

    /**
     * 根据密码改密码
     *
     * @param newpassword
     * @param username
     * @return
     */
    @Override
    public Integer updatePasswordByUsername(String newpassword, String username) {
        return customerMapper.updatePasswordByUsername(newpassword, username);
    }

    /**
     * 获取所有用户信息
     *
     * @param page
     * @return
     */
    @Override
    public List<Customer> getAllCustomer(Integer page) {
        return customerMapper.getAllCustomer(page);
    }

    /**
     * 查询用户表中有多少条数据
     *
     * @return
     */
    @Override
    public Integer queryCustomerNum() {
        return customerMapper.queryCustomerNum();
    }


}

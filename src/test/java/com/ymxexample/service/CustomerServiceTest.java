package com.ymxexample.service;

import com.ymxexample.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest {
@Autowired
private CustomerService customerService;

    @Test
    void getCustomerByUsername() {
    }

    @Test
    void addCustomer() {

        Integer i = customerService.addCustomer("zhangsan", "zhangsan");
        System.out.println("i = " + i);
    }


    @Test
    void getIdByUsername(){

        Integer id=customerService.getIdByUsername("345");
        System.out.println("ID = " + id);
    }


    @Test
    void getCustomerListByUsername(){
        List<Customer> customerList=customerService.getCustomerListByUsername("345");
        System.out.println("customerList:"+customerList);
    }

    @Test
    void updateUsernameByUsername(){
        int i= customerService.updateUsernameByUsername("345","qqq");
        System.out.println("i:"+i);
    }


    @Test
    void queryPasswordByUsername(){
        String i=customerService.queryPasswordByUsername("www");
        System.out.println("密码:"+i);
    }


    @Test
    void updatePasswordByUsername(){
        int i= customerService.updatePasswordByUsername("789","234");
        System.out.println("i:"+i);
    }

    @Test
    void getAllCustomer(){
       List<Customer> customer=customerService.getAllCustomer(1*5);
        System.out.println(customer);

    }

    @Test
    void queryCustomerNum(){
        Integer i= customerService.queryCustomerNum();
        System.out.println("I:"+i);

    }
}
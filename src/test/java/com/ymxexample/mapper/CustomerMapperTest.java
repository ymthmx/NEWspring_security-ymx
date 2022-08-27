package com.ymxexample.mapper;

import com.ymxexample.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;
    @Test
    void getCustomerByUsername() {
        Customer customer = customerMapper.getCustomerByUsername("wukong");
        System.out.println("customer = " + customer);
    }

@Test
void getIdByUsername(){
//    Customer customer = customerMapper.getIdByUsername("345");
    System.out.println("ID = " + customerMapper.getIdByUsername("zhangsan"));

}


    @Test
    void insertCustomer(){

//        Customer customer = new Customer();
//        customer.setUsername("user");
//        customer.setPassword("123");
//        customer.setValid(1);
        int i = customerMapper.insertCustomer("user","123");
        if(i == 1){
            System.out.println("评论添加成功");
        }else {
            System.out.println("评论添加失败。。");
        }
    }


    @Test
    void getCustomerListByUsername(){

        List<Customer> customerList=customerMapper.getCustomerListByUsername("456");
        System.out.println("customerList:"+customerList);
    }

    @Test
    void updateUsernameByUsername(){
        int i= customerMapper.updateUsernameByUsername("qqq","345");
        System.out.println("i:"+i);
    }


    @Test
    void queryPasswordByUsername(){
        String i=customerMapper.queryPasswordByUsername("qqq");
        System.out.println("密码:"+i);
    }


    @Test
    void getAllCustomer(){
        List<Customer> allCustomerList= customerMapper.getAllCustomer(2*5);
        System.out.println("allCustomerList:"+allCustomerList);
    }


    @Test
    void queryCustomerNum(){
        Integer i= customerMapper.queryCustomerNum();
        System.out.println("I:"+i);
    }
}
package com.ymxexample.mapper;

import com.ymxexample.domain.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {

    //根据用户账号查询用户信息

    Customer getCustomerByUsername(@Param("username") String username);



    Integer getIdByUsername(@Param("username") String username);


    /**
     * 根据用户名修改新用户名
     * @param newusername
     * @param oldusername
     * @return
     */
    Integer updateUsernameByUsername(@Param("newusername")String newusername,@Param("oldusername") String oldusername);

    /**
     * 根据密码改密码
     * @param newpassword
     * @param username
     * @return
     */
    Integer updatePasswordByUsername(@Param("newpassword")String newpassword,@Param("username")String username );
    /**
     * 通过用户名查询用户密码
     * @param username
     * @return
     */
    String queryPasswordByUsername(@Param("username")String username);

    /**
     * 向数据库添加用户账号和密码
     * @param username  账号
     * @param password  密码
     * @return  0表示失败，1 表示成功
     */
    Integer insertCustomer(@Param("username") String username, @Param("password") String password);


    /**
     * 根据登录的用户名id查询该用户信息
     * @param
     * @return
     */

    List<Customer> getCustomerListByUsername(@Param("username") String username);

    /**
     * 获取所有用户信息
     * @return
     */
    List<Customer> getAllCustomer(@Param("page") Integer page);

    /**
     * 查询用户表中有多少条数据
     * @return
     */
    Integer queryCustomerNum();
}

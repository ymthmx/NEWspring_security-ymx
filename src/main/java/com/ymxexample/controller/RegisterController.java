package com.ymxexample.controller;


import com.ymxexample.domain.Customer;
import com.ymxexample.service.CuAuthorityService;
import com.ymxexample.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CuAuthorityService cuAuthorityService;



    @RequestMapping(value = "/doRegister")
    public String doRegister(@Validated Customer _user,
                             BindingResult bindingResult,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        List<Object> list = new ArrayList();
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                list.add(e.getDefaultMessage());
            });
            System.out.println("list = " + list);
            try {
                // request.getRequestDispatcher("/showReg").forward(request, response);
                model.addAttribute("errorList", list);
                return "register";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




        // 获得表单中的参数
        String username = _user.getUsername();
        String password = _user.getPassword();
        String rePassword = request.getParameter("rePassword");
        String userlevel=request.getParameter("userlevel");

        // 进行密码二次确认
        if (!password.equals(rePassword)) { // 两次密码不一致的时候
            try {
                request.getRequestDispatcher("/userRegister").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //判断 用户账号是否唯一

        Customer user = customerService.getCustomerByUsername(username);
        if (user != null) { // 数据库中已经存在该用户
            try {
                System.out.println("注册用户>>已有该用户名");

                request.getRequestDispatcher("/userRegister").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("注册用户>>没有该用户名,可以使用");
            // 进行注册（在数据库中添加用户）
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodePassword = bCryptPasswordEncoder.encode(password);

            System.out.println("会员编号="+userlevel);

            Integer i = customerService.addCustomer(username,encodePassword);
            if (i != 1) { // 添加失败
                try {
                    request.getRequestDispatcher("/userRegister").forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            //添加VIP权限

            Integer id=customerService.getIdByUsername(username);
            Integer userLevel=Integer.parseInt(userlevel);
            cuAuthorityService.addCuAuthority(userLevel,id);


        }

        return "login/login";

    }


    @RequestMapping(value = "/userRegister")
    public String showRegisterPage(){
        return "register";
    }

}

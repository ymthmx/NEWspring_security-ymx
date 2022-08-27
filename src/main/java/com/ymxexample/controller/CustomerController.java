package com.ymxexample.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.ymxexample.domain.Authority;
import com.ymxexample.domain.Customer;
import com.ymxexample.service.AuthorityService;
import com.ymxexample.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthorityService authorityService;


    /**
     * 更改用户名
     *
     * @param _user
     * @param request
     * @param response
     * @param authentication
     * @param model1
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/updateUser")
    public String updateUsername(@Validated Customer _user,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 Authentication authentication,
                                 Model model1,
                                 Model model,
                                 Principal principal) {

//        PrintWriter writer = response.getWriter();
        //获取当前用户名
        String oldusername = authentication.getName();

        System.out.println("修改用户名>>>oldname:" + oldusername);
        // 获得表单中的参数
        String newusername = _user.getUsername();
        System.out.println("修改用户名>>>newname:" + newusername);
//        String newpassword = _user.getPassword();
//        System.out.println("newpassword:" + newpassword);
        //String oldPassword = request.getParameter("oldpassword");
        //String rePassword = request.getParameter("renewpwd");


        Customer user = customerService.getCustomerByUsername(newusername);
        if (user != null) { // 数据库中已经存在该用户
            try {
                System.out.println("修改用户名>>>已有该用户名");

//                writer.write(new ObjectMapper().writeValueAsString("已有该用户名"+newusername));
//                writer.flush();
//                writer.close();
                request.getRequestDispatcher("/user").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("修改用户名>>>用户名不重复,可以修改");
            int i = customerService.updateUsernameByUsername(newusername, oldusername);
            System.out.println("修改用户名>>>修改结果>i:" + i);
            if (i != 1) { // 添加失败
                try {
                    System.out.println("修改用户名>>>用户名更改失败");
                    request.getRequestDispatcher("/user").forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //修改后更新信息列表
//            System.out.println("修改用户名>>>当前登录用户:" + newusername);
//            List<Customer> customerList = customerService.getCustomerListByUsername(newusername);
//            List<Authority> authorityList = authorityService.getAuthorityListByUsername(newusername);
//                //  model 对象理解成 临时的存储空间
//            model1.addAttribute("customerList", customerList);
//            model.addAttribute("authorityList", authorityList);
            //<<<<<<存在bug   不采用,重新登录


        }
//        String username = principal.getName();


//        return "user";  // 逻辑视图名

        return "login/login";

    }

    /**
     * 更改密码
     *
     * @param _user
     * @param request
     * @param response
     * @param authentication
     * @param model1
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "updatePwd")
    public String updatePwd(@Validated Customer _user,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            Authentication authentication,
                            Model model1,
                            Model model,
                            Principal principal) {
        //获取当前用户名
        String oldusername = authentication.getName();

        System.out.println("修改密码>>>old:" + oldusername);
        // 获得表单中的参数
//    String newusername= _user.getUsername();
//    System.out.println("new:"+newusername);
        String newpassword = _user.getPassword();
        System.out.println("修改密码>>>newpassword:" + newpassword);
        String oldPassword = request.getParameter("oldpassword");
        String rePassword = request.getParameter("renewpwd");

        //通过用户名查询加密后的密码
        String oldpwdsql = customerService.queryPasswordByUsername(oldusername);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encodePassword = bCryptPasswordEncoder.encode("456");
//        System.out.println("密文 encodePassword="+encodePassword);

        boolean flag = bCryptPasswordEncoder.matches(oldPassword, oldpwdsql);
        System.out.println("修改密码>>>明文和密文进行比对 flag=" + flag);


        // 进行密码二次确认
        if (!newpassword.equals(rePassword)) { // 两次密码不一致的时候
            try {
                System.out.println("修改密码>>>两次密码不一致");
                request.getRequestDispatcher("/user").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (flag) {
                //更改密码

                String encodenewPassword = bCryptPasswordEncoder.encode(newpassword);
                System.out.println("修改密码>>>密文 encodePassword=" + encodenewPassword);
                int i = customerService.updatePasswordByUsername(encodenewPassword, oldusername);
                System.out.println("i:" + i);
                if (i != 1) { // 添加失败
                    try {
                        System.out.println("修改密码>>>密码更改失败");
                        request.getRequestDispatcher("/user").forward(request, response);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        //修改后更新信息列表>>>
        String username = principal.getName();

        System.out.println("修改密码>>>当前登录用户:" + username);
        List<Customer> customerList = customerService.getCustomerListByUsername(username);
        List<Authority> authorityList = authorityService.getAuthorityListByUsername(username);
        // model 对象理解成 临时的存储空间
        model1.addAttribute("customerList", customerList);
        model.addAttribute("authorityList", authorityList);
        return "user";
        //<<<<<<存在bug   不采用,重新登录


    }

    /**
     * 用户个人信息界面
     *
     * @param model1
     * @param model
     * @param principal
     * @return
     */

    @RequestMapping(value = "/user")
    public String showCustomerList(Model model1,
                                   Model model
            , Principal principal) {
        String username = principal.getName();

        System.out.println("个人信息>>>当前登录用户:" + username);
        List<Customer> customerList = customerService.getCustomerListByUsername(username);
        List<Authority> authorityList = authorityService.getAuthorityListByUsername(username);
//         model 对象理解成 临时的存储空间
        model1.addAttribute("customerList", customerList);
        model.addAttribute("authorityList", authorityList);
        return "user";  // 逻辑视图名
    }


    Integer page;

    @RequestMapping(value = "/detailuser")
    public String showAllCustomer(Model model1, Model model,Model nowpage) {
        page = 0;

        System.out.println("管理个人信息页面>>>");
        List<Customer> allcustomerList = customerService.getAllCustomer(page * 5);
//    List<Authority> authorityList = authorityService.getAuthorityListByUsername(username);
//         model 对象理解成 临时的存储空间
        model1.addAttribute("allcustomerList", allcustomerList);
        nowpage.addAttribute("nowpage",1);
//    model.addAttribute("authorityList", authorityList);
        return "detailuser";  // 逻辑视图名
    }

    //查询下一页信息
    @RequestMapping(value = "nextpage")
    public String showNextPage(Model model1,Model nowpage) {
        System.out.println("管理个人信息页面>>>下一页");
        Integer allCount= customerService.queryCustomerNum();
        System.out.println("管理个人信息页面>>>下一页>表总条数:"+allCount);
        Integer allpage;
        if (allCount%5==0){
             allpage=allCount/5-1;
        }else {
            allpage=allCount/5;
        }

        if (page<allpage){
            page++;
            System.out.println("管理个人信息页面>>>当前页数:"+(page+1));
            List<Customer> allcustomerList = customerService.getAllCustomer(page * 5);
//    List<Authority> authorityList = authorityService.getAuthorityListByUsername(username);
//         model 对象理解成 临时的存储空间
            model1.addAttribute("allcustomerList", allcustomerList);
            nowpage.addAttribute("nowpage",page+1);
//    model.addAttribute("authorityList", authorityList);
        }else {

            List<Customer> allcustomerList = customerService.getAllCustomer(allpage * 5);
            model1.addAttribute("allcustomerList", allcustomerList);
            nowpage.addAttribute("nowpage",allpage+1);
        }


        return "detailuser";  // 逻辑视图名
    }

    //查询上一页信息
    @RequestMapping(value = "pastpage")
    public String showPastPage(Model model1,Model nowpage) {
        System.out.println("管理个人信息页面>>>上一页");

        if (page > 0) {
            page--;
            System.out.println("管理个人信息页面>>>当前页数:"+(page+1));
            List<Customer> allcustomerList = customerService.getAllCustomer(page * 5);
//    List<Authority> authorityList = authorityService.getAuthorityListByUsername(username);
//         model 对象理解成 临时的存储空间
            model1.addAttribute("allcustomerList", allcustomerList);
            nowpage.addAttribute("nowpage",page+1);
//    model.addAttribute("authorityList", authorityList);

        } else {
            System.out.println("管理个人信息页面>>>上一页>>>已经是第一页了");
            List<Customer> allcustomerList = customerService.getAllCustomer(0);
            model1.addAttribute("allcustomerList", allcustomerList);
            nowpage.addAttribute("nowpage",1);
        }
        return "detailuser";  // 逻辑视图名
    }

}

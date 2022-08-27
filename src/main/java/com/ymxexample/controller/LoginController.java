package com.ymxexample.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController  {




    @RequestMapping(value = "/userLogin")
    public String login(){

        return "login/login";
    }


    @RequestMapping(value = "/csrf")
    public String csrf(){

        return "csrf/csrfTest";
    }


}

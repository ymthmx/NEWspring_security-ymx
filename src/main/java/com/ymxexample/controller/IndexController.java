package com.ymxexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


    @RequestMapping("/detail/{type}/{num}")
    public String index1(@PathVariable("type") String type,
                         @PathVariable("num") String num){
        return "detail/"+type+"/"+num;
    }

    @RequestMapping(value = "/commonpage")
    public String commonpage(){
        return "commonpage";
    }

    @RequestMapping(value = "/vippage")
    public String vippage(){
        return "vippage";
    }
}

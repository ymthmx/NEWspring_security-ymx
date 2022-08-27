package com.ymxexample.config;


import com.ymxexample.domain.Customer;
import com.ymxexample.service.CuAuthorityService;
import com.ymxexample.service.CustomerService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


// spring security 框架配置类 对该框架进行一些个性化设置
@EnableWebSecurity
@Controller
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
    private UserDetailsService userDetailsService;
    // 密码的加密和解密功能


    // 使用jdbc 技术访问数据库
    @Resource
    private DataSource dataSource;


    /**
     * 持久化Token存储
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr=new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

                auth
                .userDetailsService(userDetailsService)    //是自定义的 userDetailsService发挥作用
                .passwordEncoder(bCryptPasswordEncoder)
                        ;   //密码的加密解密功能,指定密码加密器

//        super.configure(auth);
    }
//使用main方法进行测试
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePassword = bCryptPasswordEncoder.encode("456");
        System.out.println("密文 encodePassword="+encodePassword);

        System.out.println("Lll"+bCryptPasswordEncoder.upgradeEncoding(encodePassword));
        boolean flag=bCryptPasswordEncoder.matches("abc",encodePassword);
        System.out.println("明文和密文进行比对 flag=" + flag);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {





        // 任何人都可以访问的路径
        http.authorizeRequests()

                .antMatchers("/detail/common/**").hasAnyRole("common","vip","admin")       // 文件 detail/common/下访问需要普通,超级VIP
                .antMatchers("/detail/vip/**").hasAnyRole("vip","admin")                    //detail/vip/下文件需要超级VIP
                .antMatchers("/**").permitAll()  // 所有身份的人都可以访问
                .antMatchers("/login/**","/error/**").permitAll()  //静态资源可以访问
                .antMatchers("/user").permitAll()
                .antMatchers("/foot").permitAll()
                .antMatchers("/commonpage").permitAll()
                .antMatchers("/detail/visitor/**").permitAll()  // 静态资源可以访问
//                .antMatchers("/login/**").permitAll()

                .anyRequest().authenticated()                   //没有权限,任何请求都需要事先登录
        ;

        // 有特定身份的人可以访问的路径
//        http.authorizeRequests()
//                ;


        // 登录功能的配置
        http.authorizeRequests().and()
                // 开启登录功能
                .formLogin()
                // 访问登录页面（自定义登录路径）
                .loginPage("/userLogin").permitAll()
                //  指定登录处理的 url ，对应action的值
                .loginProcessingUrl("/userLogin")
                .usernameParameter("name")
                .passwordParameter("pwd")

                //    登录成功后，访问首页 /
                .defaultSuccessUrl("/")
                // 登录失败后，访问 /userLogin?error
                .failureUrl("/userLogin?error");




        //退出
        http
                //开启退出功能
                .logout()
                //访问 /logout 执行退出功能
                .logoutUrl("/logout")
                // 退出成功,访问首页 /
                .logoutSuccessUrl("/");


        // 记住我
        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(60*60*24*7)  // 7 天
                .tokenRepository(this.tokenRepository());


    }
}

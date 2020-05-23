package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *  我们目 前只是需要用到BCrypt密码加密的部分，所以我们要添加一个配置类，配置为
    所有地址 都可以匿名访问。

 * 安全配置类 (springboot 不提供配置文件,所以需要些配置类)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*authorizeRequests所有security全注解配置实现的开端,表示说明需要的权限
          需要的权限分为两部分,第一部分是拦截的路径,第二部分访问该路径需要的权限
          antMatchers :表示拦截什么路径permitAll 任何权限都可以访问
          anyRequest :任何请求,authenticated认证后才能访问
          .and().csrf().disable() 表示csrf拦截失效
        * */
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
    /*
    任何应用考虑到安全，绝不能明文的方式保存密码。密码应该通过哈希算法进行加密。
    有很多标准的算法比如SHA或者MD5，结合salt(盐)是一个不错的选择。 Spring Security
    提供了BCryptPasswordEncoder类,实现Spring的PasswordEncoder接口使用BCrypt强 哈希方法来加密密码


    * 我们在添加了spring security依赖后，所有的地址都被spring security所控制了，
    * 我们目 前只是需要用到BCrypt密码加密的部分，所以我们要添加一个配置类，配置为
    * 所有地址 都可以匿名访问。
     */
}

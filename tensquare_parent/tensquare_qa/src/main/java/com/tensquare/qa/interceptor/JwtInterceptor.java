package com.tensquare.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器 方法执行前拦截
 * @author BinPeng
 * @date 2020/5/22 20:49
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //放行所有
        //拦截器负责 吧有请求头中包含token的令牌 进行解析验证
        String header = request.getHeader("Authorization");
        if (header!=null && !"".equals(header)){
            //如果头部包含Authorization信息 对其进行解析
            if (header.startsWith("Bearer ")){
                String token = header.substring(7);//截取得到token令牌
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);//解析token
                    String roles = (String) claims.get("roles");
                    if (roles != null && roles.equals("admin")){
                        request.setAttribute("claims_admin",token);
                    }
                    if (roles != null && roles.equals("user")){

                        request.setAttribute("claims_user",token);
                    }

                }catch (Exception e){
                    throw  new RuntimeException("令牌不正确!");
                }
            }
        }
        return true;
    }
}

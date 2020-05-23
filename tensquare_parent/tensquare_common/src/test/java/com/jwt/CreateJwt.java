package com.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 测试JWT 登录验证
 * 生成 令牌
 * @author BinPeng
 * @date 2020/5/22 15:20
 */
public class CreateJwt {
    /*
    setIssuedAt用于设置签发时间
    signWith用于设置签名秘钥
    * */
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder()
                .setId("666")
                .setSubject("测试")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(new Date().getTime()+60000))//过期时间
                .claim("role","admin")//存储自己定义数据
                ;
        System.out.println(builder.compact());
    }
}

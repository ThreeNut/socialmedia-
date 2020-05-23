package com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/** 有状态登录 服务器端保存登录信息 和浏览器作对比
 *  无状态登录 服务器不保存用户的登录信息！
 *  客户端的每次请求必须具备自描述信息（jwt）
 *
 *  无状态登录的流程：
 * • 当客户端第一次请求服务时，服务端对用户进行信息认证（登录）
 * • 认证通过，将用户身份信息(不包含密码)进行加密形成token，返回给客户端，作为登录凭证
 * • 以后每次请求，客户端都携带认证的token
 * • 服务的对token进行解密，判断是否有效。
 * 解析
 * @author BinPeng
 * @date 2020/5/22 15:33
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        Claims itcast = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLmtYvor5UiLCJpYXQiOjE1OTAxMzM5NzYsImV4cCI6MTU5MDEzNDAzNiwicm9sZSI6ImFkbWluIn0.m4Ro0IFYVwJ1DK0-D7W9DzBW5Su9Srl0TFbz569RxxM")
                .getBody();
        System.out.println("用户id:"+itcast.getId());
        System.out.println("用户名:"+itcast.getSubject());
        System.out.println("登录时间:"+new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(itcast.getIssuedAt()));
        System.out.println("认证过期时间:"+new SimpleDateFormat("yyyy-MM-dd-HH:mm").format(itcast.getExpiration()));
        System.out.println("自定义属性:"+itcast.get("role"));
    }
}

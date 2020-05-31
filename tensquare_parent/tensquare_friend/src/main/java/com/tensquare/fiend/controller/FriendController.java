package com.tensquare.fiend.controller;

import com.tensquare.fiend.client.UserClient;
import com.tensquare.fiend.dao.NoFriendDao;
import com.tensquare.fiend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 交友
 * @author BinPeng
 * @date 2020/5/29 18:35
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired //远程调用user模块
    private UserClient userClient;


// http://localhost:9010/friend/like/1266715828989071360/1
    @PutMapping(value = "/like/{friendid}/{type}") //添加好友
    public Result addFriend(@PathVariable String friendid , @PathVariable String type){
        //验证是否登录,并且拿到当前登录用户的id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null ){
            //没登录
            return new Result(false,StatusCode.ERROR,"权限不足");
        }
        String userid = claims.getId();//得到登录用户的id
        //判断添加好友还是非好友
        if (type!=null){
            if (type.equals("1")){
                //添加好友

                int flag = friendService.addFriend(userid,friendid);
                if (flag == 0){
                    return new Result(false,StatusCode.ERROR,"不能重复添加好友");
                }
                if (flag == 1){
                    userClient.updateFanscountAndFollowcount(userid,friendid,1);//添加粉丝和关注
                    return new Result(true,StatusCode.OK,"添加成功!");
                }
            }else if (type.equals("2")){
                //添加非好友(不喜欢)
              int flag = friendService.addNoFriend(userid,friendid);
                if (flag == 0){
                    return new Result(false,StatusCode.ERROR,"已经添加不喜欢！");
                }
                if (flag == 1){
                    return new Result(true,StatusCode.OK,"不喜欢成功!");
                }
            }
             return new Result(false, StatusCode.ERROR,"参数异常!");
        }else {
            return new Result(false, StatusCode.ERROR,"参数异常!");
        }

    }
    //删除好友
    @DeleteMapping("/{friendid}")
    public Result deleteFriend(@PathVariable String friendid){
        //验证是否登录,并且拿到当前登录用户的id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null ){
            //没登录
            return new Result(false,StatusCode.ERROR,"权限不足");
        }
        //当前登录用户的id
        String userid = claims.getId();
        friendService.deleteFriend(userid,friendid);
        userClient.updateFanscountAndFollowcount(userid,friendid,-1);
        return new Result(true,StatusCode.OK,"删除OK！");
    }
}

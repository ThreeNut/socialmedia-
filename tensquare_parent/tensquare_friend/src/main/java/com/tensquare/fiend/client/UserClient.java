package com.tensquare.fiend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author BinPeng
 * @date 2020/5/31 16:13
 */
@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 更新用户的关注数  以及关注用户的粉丝数
     * @param userid
     * @param friendid
     */
    @PutMapping("/user/{userid}/{friendid}/{x}")
    public void updateFanscountAndFollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid , @PathVariable("x") int x);

}

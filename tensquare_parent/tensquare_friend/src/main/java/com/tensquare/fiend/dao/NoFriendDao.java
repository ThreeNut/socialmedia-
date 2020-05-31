package com.tensquare.fiend.dao;

import com.tensquare.fiend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BinPeng
 * @date 2020/5/31 11:29
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    public NoFriend findByUseridAndFriendid(String userid,String friendid);

}

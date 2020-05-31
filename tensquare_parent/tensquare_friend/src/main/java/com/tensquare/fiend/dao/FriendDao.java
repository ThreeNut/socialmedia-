package com.tensquare.fiend.dao;

import com.tensquare.fiend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BinPeng
 * @date 2020/5/30 10:31
 */
public interface FriendDao extends JpaRepository<Friend,String> , JpaSpecificationExecutor<Friend> {
    //查询 是否已经添加为好友
    public Friend findByUseridAndFriendid(String userid,String friendid);

    //如果不是好友点击关注后如果是互关  更新数据
    @Transactional
    @Modifying //在 @Query 注解中编写 JPQL 语句， 但必须使用 @Modifying 进行修饰. 以通知 SpringData， 这是一个 UPDATE 或 DELETE 操作
    @Query(value = "UPDATE tb_friend SET islike= ? WHERE userid = ? AND friendid = ? ",nativeQuery = true)
    public void updateIslike(String islike,String userid,String friendid);

    //删除好友
    @Transactional
    @Modifying //在 @Query 注解中编写 JPQL 语句， 但必须使用 @Modifying 进行修饰. 以通知 SpringData， 这是一个 UPDATE 或 DELETE 操作
    @Query(value = "DELETE FROM tb_friend WHERE userid = ? AND friendid = ? ",nativeQuery = true)
    public void deletefriend(String userid, String friendid);
}

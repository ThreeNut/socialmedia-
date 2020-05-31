package com.tensquare.fiend.service;

import com.tensquare.fiend.dao.FriendDao;
import com.tensquare.fiend.dao.NoFriendDao;
import com.tensquare.fiend.pojo.Friend;
import com.tensquare.fiend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BinPeng
 * @date 2020/5/30 10:15
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid是否有数据 (有重复添加返回0)
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend!=null){
            return 0;
        }

        //直接添加好友
        Friend  friend1 = new Friend();
        friend1.setUserid(userid);
        friend1.setFriendid(friendid);
        friend1.setIslike("0");
        friendDao.save(friend1);//保存好友
        //判断从friend到userID是否有数据 如果有双方状态改为1  (数据库表没有id friend到userid 意为互换数据了)
        if (friendDao.findByUseridAndFriendid(friendid,userid)!=null){
            //把双方的islike都改为1
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }
        return 1;
    }

    //不喜欢
    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend!=null){
            //已经是非好友了
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    //删除好友
    public void deleteFriend(String userid, String friendid) {
        //删除好友表中userid到 friend数据
        friendDao.deletefriend(userid,friendid);
        //更新friendid到userid的is
        friendDao.updateIslike("0",friendid,userid);
        //非好友添加数据
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}

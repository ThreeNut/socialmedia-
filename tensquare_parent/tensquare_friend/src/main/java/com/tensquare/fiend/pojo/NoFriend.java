package com.tensquare.fiend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *
 * 实体
 * @author BinPeng
 * @date 2020/5/30 10:32
 */
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class) //表明联合主键
public class NoFriend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

}

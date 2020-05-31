package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	public User findByMobile (String bobile);

	@Modifying
	@Query(value = "UPDATE tb_user SET  fanscount=fanscount + ? WHERE id= ? ",nativeQuery = true)
    public void updatefanscount(int x, String friendid);//用户点击关注后 被点击的用户粉丝数+1


	@Modifying
	@Query(value = "UPDATE tb_user SET  followcount=followcount + ? WHERE id= ? ",nativeQuery = true)
    public void updatefollowcount(int x, String userid);//用户点击关注后 用户自己关注+1
}

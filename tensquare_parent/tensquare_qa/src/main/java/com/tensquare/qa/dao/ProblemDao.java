package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新回复
     * @param labelid
     * @param pageable
     * @return
     */
	@Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id=problemid AND labelid = ? ORDER BY replytime DESC" , nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);

    /**
     * 最热评论
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id=problemid AND labelid = ? ORDER BY reply DESC" , nativeQuery = true)
    public Page<Problem> newhost(String labelid, Pageable pageable);

    /**
     * 根据标签ID查询等待回答列表 回复为0 则说明需要回复
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id=problemid AND labelid = ? AND reply=0 ORDER BY createtime DESC" , nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);
}

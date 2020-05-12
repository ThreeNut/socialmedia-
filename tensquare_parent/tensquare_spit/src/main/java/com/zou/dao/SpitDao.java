package com.zou.dao;

import com.zou.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.cdi.MongoRepositoryBean;

/**
 *  吐槽
 * @author BinPeng
 * @date 2020/5/12 9:35
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    /**
     * 根据上级ID查询吐槽列表（分页
     * @param parentId
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentId, Pageable pageable);
}

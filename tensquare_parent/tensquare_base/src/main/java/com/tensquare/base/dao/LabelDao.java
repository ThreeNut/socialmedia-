package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * jpa接口
 * @author BinPeng
 * @date 2020/5/6 11:31
 */
public interface LabelDao extends JpaRepository<Label , String>, JpaSpecificationExecutor<Label> {

}

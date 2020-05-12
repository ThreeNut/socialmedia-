package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *  文章评论
 * @author BinPeng
 * @date 2020/5/12 21:46
 */
public interface CommentDao extends MongoRepository<Comment,String> {

}

package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 *  文章评论
 * @author BinPeng
 * @date 2020/5/12 21:48
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;
    //添加评论
    public void  add(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }
    //查询所有评论
    public List<Comment> findAll() {
       return  commentDao.findAll();
    }
}

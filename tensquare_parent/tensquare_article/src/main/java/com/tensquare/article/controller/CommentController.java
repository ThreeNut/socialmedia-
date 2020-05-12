package com.tensquare.article.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  文章评论
 * @author BinPeng
 * @date 2020/5/12 21:50
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping()
    public Result findAll(){

        return new Result(true,StatusCode.OK,"查询所有ok", commentService.findAll());
    }
    @PostMapping()
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"评论成功!");
    }

}

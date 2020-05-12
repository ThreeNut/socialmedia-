package com.zou.controller;

import com.zou.pojo.Spit;
import com.zou.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;


import javax.annotation.Resource;
import java.util.Date;

/**
 *  吐槽
 * @author BinPeng
 * @date 2020/5/12 9:43
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired //按照类型注入   @Resource按照名称注入
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping()
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询所有吐槽",spitService.findAll());
    }
    @GetMapping(value = "/{spitId}")
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"根据id查询",spitService.findById(spitId));
    }
    //保存...
    @PostMapping()
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存ok");
    }

    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spit.set_id(id);
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"修改ok");
    }
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id){
        spitService.delete(id);
        return new Result(true,StatusCode.OK,"删除ok");
    }

    /**
     * 根据上级ID查询吐槽分页数据
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId,@PathVariable int page,@PathVariable int size){
        Page<Spit> listSpits = spitService.findByParentid(parentId, page, size);
        return new Result(true,StatusCode.OK," 根据上级ID查询吐槽分页数据ok",
               new PageResult<Spit>(listSpits.getTotalElements(),listSpits.getContent()));
    }

    //点赞        (防止重复点赞)
    @PutMapping(value = "/thumbup/{id}")
    public Result updateThumbup(@PathVariable String id){
        //判断用户是否点过赞(防止重复点赞)
        String userid="1012";// 后边我们会修改为当前登陆的用户
        if (redisTemplate.opsForValue().get("thumbup_"+userid+"_"+id)!=null){
            return new Result(false,StatusCode.ERROR,"已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup_"+userid+"_"+id,"1");
        return new Result(true,StatusCode.OK,"点赞ok");
    }

}

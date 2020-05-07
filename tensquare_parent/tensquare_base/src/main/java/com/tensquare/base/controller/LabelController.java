package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * @author BinPeng
 * @date 2020/5/6 11:02
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService service;
    @GetMapping()
    public Result findAll(){
        //int a=1/0;
        return new Result(true, StatusCode.OK,"查询ok!",service.findAll());
    }

    @GetMapping(value = "/{findById}")
    public Result findById(@PathVariable String findById){
        return new Result(true, StatusCode.OK,"查询ok!",service.findById(findById));
    }

    //更新
    @PutMapping(value = "/{update}")
    public Result update(@RequestBody Label label,@PathVariable String update){
        label.setId(update);
        service.update(label);
        return new Result(true, StatusCode.OK,"更新完成!");
    }
    @PostMapping()
    public Result save(@RequestBody Label label){
        service.save(label);
        return new Result(true, StatusCode.OK,"查询ok!");
    }

    @DeleteMapping(value = "/{deleteById}")
    public Result deleteById(@PathVariable String deleteById){
        service.deleteById(deleteById);
        return new Result(true, StatusCode.OK,"删除ok!");
    }
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list = service.findSearch(label);
        return new Result(true,StatusCode.OK,"条件查询!",list);
    }
    //分页查询
    @PostMapping(value = "/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
       Page<Label> pageData = service.findPageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"条件分页查询!",new PageResult<Label>(pageData.getTotalElements(),
                pageData.getContent()));
    }
}

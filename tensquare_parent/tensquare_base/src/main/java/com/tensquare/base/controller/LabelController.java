package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}

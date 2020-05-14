package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author BinPeng
 * @date 2020/5/14 15:47
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleSearchService articleService;

    //http://localhost:9007/article  测试
    @PostMapping()
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"搜索数据插入");
    }

    //测试 http://127.0.0.1:9007/article/search/11/1/3
    //模糊搜索 关键字搜索(题目 或者文章)
    @GetMapping(value = "/search/{keywords}/{page}/{size}") //keywords 是搜索关键字 可以是 题目或者文章内容 的关键字
    public Result findByKey(@PathVariable String keywords,@PathVariable int page,@PathVariable int size){
        Page<Article> pageData=articleService.findByKey(keywords,page,size);
        return new Result(true,StatusCode.OK,"分页查询搜索数据",
                new PageResult<Article>(pageData.getTotalPages(),pageData.getContent()));
    }
/*   问题}{iO6JFyVSQWu2UQgHPJ0XIw}{192.168.0.102}{192.168.0.102:9300}]
*    当使用docker的elasticsearch时报以上错误9300不能使用  但是端口9200可以使用
* 此时需要调试宿主机的配置...2020年5月14日22:48:38明天继续
* */
}

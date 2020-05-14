package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 *  搜索
 * @author BinPeng
 * @date 2020/5/14 11:32
 */
@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleDao;
    @Autowired
    private IdWorker idWorker;

    public void save(Article article){
//        article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }

    public Page<Article> findByKey(String title, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        // 第一个title 是题目关键字  第二个title是文章内容的关键字
        return articleDao.findByTitleOrContentLike(title,title,pageRequest);
    }
}

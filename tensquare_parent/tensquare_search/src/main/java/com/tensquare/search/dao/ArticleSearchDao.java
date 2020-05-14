package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author BinPeng
 * @date 2020/5/14 11:29
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    //ElasticsearchRepository<Article,String>   就是ElasticsearchRepository<实体类型,主键id类型>
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}

package com.tensquare.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 *  创建搜索文章的实体类
 * @author BinPeng
 * @date 2020/5/14 10:33
 */
//indexName = "索引名"   type = "索引类型"
@Document(indexName = "tensquare_elasticsearch",type = "article")
public class Article {

    @Id
    private String id;

    //index = true 代表此字段是索引值 analyzer = "ik_max_word" 按照哪个分词器分词 searchAnalyzer = "ik_max_word" 查询的时候按照什么分词
    //  是否索引,就是该域是否能被搜索到  index
    //  是否分词,就是表示搜索的时候是整体匹配还是单词匹配 analyzer
    //  是否存储,就是在页面上显示哪些信息(例如本Article类只存储了tb_article表的四个字段  id,title,content,state)
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title; //文章题目

    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content; //内容

    private String state; //状态 不需要索引

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

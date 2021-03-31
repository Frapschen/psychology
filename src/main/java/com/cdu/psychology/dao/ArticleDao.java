package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ArticleDao {
    @Select("select * from article")
    List<Article> getArticle();

    @Select("select * from article where id = #{id}")
    Article getArticleById(int id);

}

package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ArticleDao {
    List<Article> getArticle();
    Article getArticleById(int id);

}

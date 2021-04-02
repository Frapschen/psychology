package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ArticleDao {
    List<Article> getArticle();
    Article getArticleById(int id);

    @Insert("insert into article (title,introduce,content,created_time) values (#{title},#{introduce},#{content},#{created_time})")
    int putArticle(Article article);

    @Update("update article set title=#{title},introduce=#{introduce},content=#{content} where id = #{id}")
    int updateArticle(Article article);

    @Delete("delete from article where id=#{id}")
    int deleteArticle(int id);

}

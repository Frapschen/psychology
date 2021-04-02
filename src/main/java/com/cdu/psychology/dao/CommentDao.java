package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CommentDao {

    @Select("select * from conmment where ")
    List<Comment> getCommentsByArticleId();

    @Insert("insert into comment (content,user_id,article_id) values (#{content},#{user_id},#{article_id})")
    int setComment(int article_id, int user_id, String content);

    @Delete("delete from comment where id=#{id}")
    int deleteComment(int id);

    @Delete("delete from comment where article_id=#{article_id}")
    int deleteCommentsByArticleId(int article_id);
}

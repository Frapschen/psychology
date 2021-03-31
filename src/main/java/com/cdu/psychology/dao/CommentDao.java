package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentDao {

    @Select("select * from conmment where ")
    List<Comment> getCommentsByArticleId();
}

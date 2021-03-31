package com.cdu.psychology.service;

import com.cdu.psychology.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {
    PageInfo<Comment> getCommentsById(int article_id,int page,int size);
}

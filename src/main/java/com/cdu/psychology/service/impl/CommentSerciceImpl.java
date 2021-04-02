package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.CommentDao;
import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.Comment;
import com.cdu.psychology.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CommentSerciceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Override
    public PageInfo<Comment> getCommentsById(int article_id, int page, int size) {
        return null;
    }

    @Override
    public int setComment(int article_id, int user_id, String content) {
        return commentDao.setComment(article_id, user_id, content);
    }

    @Override
    public int deleteComment(int id) {
        return commentDao.deleteComment(id);
    }
}

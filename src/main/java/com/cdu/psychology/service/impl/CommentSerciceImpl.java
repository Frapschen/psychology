package com.cdu.psychology.service.impl;

import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.Comment;
import com.cdu.psychology.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentSerciceImpl implements CommentService {


    @Override
    public PageInfo<Comment> getCommentsById(int article_id, int page, int size) {
        return null;
//        PageHelper.startPage(page, size);
//        List<Comment> commentList = articleDao.getArticle();
//        PageInfo<Article> pageInfo = new PageInfo<Article>(commentList);
//        return pageInfo;
    }
}

package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.ArticleDao;
import com.cdu.psychology.dao.CommentDao;
import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.ArticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticeService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public PageInfo<Article> findAllArticeByPageS(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> lists = articleDao.getArticle();
        PageInfo<Article> pageInfo = new PageInfo<>(lists);
        return pageInfo;
    }
    @Override
    public Article getArticeById(int id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public int putArticle(Article article) {
        return articleDao.putArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

    @Override
    @Transactional
    public int deleteArticle(int id) {
        int a_code = articleDao.deleteArticle(id);
        int c_code = commentDao.deleteCommentsByArticleId(id);
        if(a_code !=0){
            return 1;
        }
        return 0;
    }
}

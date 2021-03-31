package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.ArticleDao;
import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.ArticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticeService {

    @Autowired
    ArticleDao articleDao;

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
}

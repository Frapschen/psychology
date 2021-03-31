package com.cdu.psychology.service;

import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.User;
import com.github.pagehelper.PageInfo;

public interface ArticeService {
    PageInfo<Article> findAllArticeByPageS(int pageNum, int pageSize);
    Article getArticeById(int id);
}

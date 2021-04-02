package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.ArticeService;
import com.cdu.psychology.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {

    @Autowired
    private ArticeService articeService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Map<String, Object> getArticlesList(@RequestParam(defaultValue = "1", required = false) int page,
                                                    @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        PageInfo<Article> queryResult = articeService.findAllArticeByPageS(page, size);
        data.put("code",200);
        data.put("data",queryResult);
        return data;
    }

    @GetMapping("/{id}")
    public Map<String,Object> geArticleById(@PathVariable(required = true) int id){
        Map<String, Object> data = new HashMap<>();
        Article article = articeService.getArticeById(id);
        if (article==null){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        data.put("article",article);
        return data;
    }

    @DeleteMapping("/{article_id}/{user_id}")
    public Map<String,Object> deleteArticleById(@PathVariable(name = "article_id",required = true)int article_id,
                                                @PathVariable(name = "user_id",required = true)int user_id){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        if(articeService.deleteArticle(article_id)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;
    }
    @PutMapping("/{user_id}")
    public Map<String,Object> putArticle(@PathVariable(name = "user_id",required = true)int user_id,
                                         @RequestParam(name = "title",required = true)String title,
                                         @RequestParam(name = "introduce",required = true)String introduce,
                                         @RequestParam(name = "content",required = true)String content,
                                         @RequestParam(name = "created_time",required = true)String created_time){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        Article article = new Article();
        article.setTitle(title);
        article.setIntroduce(introduce);
        article.setContent(content);
        article.setCreated_time(created_time);
        if(articeService.putArticle(article)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;
    }
    @PostMapping("/{article_id}/{user_id}")
    public Map<String,Object> postArticle(@PathVariable(name = "article_id",required = true)int article_id,
                                          @PathVariable(name = "user_id",required = true)int user_id,
                                          @RequestParam(name = "title",required = true)String title,
                                          @RequestParam(name = "introduce",required = true)String introduce,
                                          @RequestParam(name = "content",required = true)String content){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        Article article = new Article();
        article.setId(article_id);
        article.setTitle(title);
        article.setIntroduce(introduce);
        article.setContent(content);
        if(articeService.updateArticle(article)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;
    }

}

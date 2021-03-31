package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Article;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.ArticeService;
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

    @DeleteMapping("/{id}")
    public Map<String,Object> deleteArticleById(@PathVariable(required = true) int id){
        Map<String, Object> data = new HashMap<>();
        data.put("message","sucess,deleteArticleById = "+id);
        data.put("code",200);
        return data;
    }
    @PutMapping()
    public Map<String,Object> putArticle(@RequestBody Article article){
        Map<String, Object> data = new HashMap<>();
        data.put("receipt",article);
        return data;
    }
    @PostMapping()
    public Map<String,Object> postArticle(@RequestBody Article article){
        Map<String, Object> data = new HashMap<>();
        data.put("receipt",article);
        return data;
    }

}

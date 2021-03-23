package com.cdu.psychology.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleController {

    @GetMapping("/v1/article/list")
    public Map<String, Object> getArticlesList(@RequestParam(defaultValue = "1", required = false) String page,
                                                    @RequestParam(defaultValue = "10", required = false) String size){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello");
        return data;
    }

    @GetMapping("/v1/article/{id}")
    public Map<String,Object> geArticleById(@PathVariable int id){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello,article id = "+id);
        return data;
    }
}

package com.example.psychology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller("/article")
public class ArticleController {

    @GetMapping("/list")
    public Map<String, Object> getArticlesList(@RequestParam(defaultValue = "1", required = false) String page,
                                                    @RequestParam(defaultValue = "10", required = false) String size){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello");
        return data;
    }

    @GetMapping("/{id}")
    public Map<String,Object> geArticleById(@PathVariable String id){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello,article id = "+id);
        return data;
    }
}

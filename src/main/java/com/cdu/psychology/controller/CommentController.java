package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Comment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {
    @PutMapping("/v1/comment/{article_id}")
    public Map<String, Object> putComment(@PathVariable(name = "article_id",required = true) int id,
                                          @RequestBody Comment comment){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello article_id = "+id);
        data.put("recipt",comment);
        return data;
    }
}

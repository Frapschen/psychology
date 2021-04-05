package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Comment;
import com.cdu.psychology.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PutMapping("/{article_id}/{user_id}")
    public Map<String, Object> putComment(@PathVariable(name = "article_id",required = true) int article_id,
                                          @PathVariable(name = "user_id",required = true) int   user_id,
                                          @RequestParam(name = "content",required = false,defaultValue = "") String content){
        Map<String, Object> data = new HashMap<>();
        if(commentService.setComment(article_id,user_id,content)!=1){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteComment(@PathVariable(name = "id",required = true) int id){
        Map<String, Object> data = new HashMap<>();
        if(commentService.deleteComment(id)!=1){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;
    }
    @GetMapping()
    public Map<String, Object> getComment(@RequestParam(name = "article-id",required = true) int id,
                                          @RequestParam(name = "page",required = false,defaultValue = "1") int page,
                                          @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        Map<String, Object> data = new HashMap<>();
        data.put("message1","hello article = "+id);
        data.put("message2","hello page = "+page);
        data.put("message3","hello size = "+size);
        data.put("message3","hello total = "+100);
        return data;
    }
}

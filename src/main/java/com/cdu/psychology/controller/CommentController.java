package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {
    @PutMapping("/{article-id}/{user-id}")
    public Map<String, Object> putComment(@PathVariable(name = "article_id",required = true) int article_id,
                                          @PathVariable(name = "user_id",required = true) int   user_id,
                                          @RequestBody Comment comment){
        Map<String, Object> data = new HashMap<>();
        data.put("message1","hello article_id = "+article_id);
        data.put("message2","hello user_id = "+user_id);
        data.put("recipt",comment);
        return data;
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteComment(@PathVariable(name = "id",required = true) int id){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello comment = "+id);
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

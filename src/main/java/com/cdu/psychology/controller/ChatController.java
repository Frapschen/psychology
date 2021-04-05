package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Chat;
import com.cdu.psychology.service.ChatService;
import com.cdu.psychology.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/chat")
public class ChatController {

    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @GetMapping("/list")
    public Map<String, Object> getRoomList(){
        Map<String, Object> data = new HashMap<>();
        List<Chat> chatList = chatService.getChats();
        if(chatList==null){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        data.put("data",chatList);
        return data;
    }

    @PutMapping("/{user_id}")
    public Map<String, Object> putRoom(@PathVariable(name = "user_id") int user_id,
                                       @RequestParam(name = "name",required = true)String name,
                                       @RequestParam(name = "introduce",required = true)String introduce){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        Chat chat = new Chat();
        chat.setName(name);
        chat.setIntroduce(introduce);
        chat.setRoom(UUID.randomUUID().toString().replaceAll("-", ""));
        if(chatService.putChat(chat)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;
    }

    @DeleteMapping("/{user_id}")
    public Map<String, Object> deleteRoom(@PathVariable(name = "user_id") int user_id,
                                          @RequestParam(name = "id",required = true)int id){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        if(chatService.deleteChat(id)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        return data;

    }

}

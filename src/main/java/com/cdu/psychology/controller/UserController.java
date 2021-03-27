package com.cdu.psychology.controller;

import com.cdu.psychology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public Map<String, Object> login(@RequestParam(name = "username",required = true) String username,
                                     @RequestParam(name = "password",required = true) String password,
                                     @RequestParam(name = "code",required = true) int code){
        Map<String, Object> data = new HashMap<>();
        data.put("message1",username);
        data.put("message2",password);
        data.put("message3",code);
        return data;
    }
    @PutMapping()
    public Map<String, Object> register(@RequestParam(name = "username",required = true) String username,
                                        @RequestParam(name = "password",required = true) String password,
                                        @RequestParam(name = "image",required = false) MultipartFile image,
                                        @RequestParam(name = "code",required = true) int code){
        Map<String, Object> data = new HashMap<>();
        data.put("message1",username);
        data.put("message2",password);
        data.put("message3",image);
        data.put("message4",code);
        return data;
    }
    @GetMapping()
    public Map<String, Object> getUserList(@RequestParam(defaultValue = "0", required = false) int page,
                                           @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        data.put("message","hello");
        data.put("message1",page);
        return data;
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable(name = "id") int id){
        Map<String, Object> data = new HashMap<>();
        data.put("message delete user id",id);
        return data;
    }
    @GetMapping("/ping")
    public Map<String, Object> pingUser(){
        Map<String, Object> data = new HashMap<>();
        int count = userService.pingUser();
        data.put("User Count",count);
        return data;
    }
}

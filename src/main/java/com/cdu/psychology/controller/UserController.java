package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Code;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.CommonService;
import com.cdu.psychology.service.UserService;
import com.cdu.psychology.utils.Base64Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam(name = "username",required = true) String username,
                                     @RequestParam(name = "password",required = true) String password,
                                     @RequestParam(name = "code",required = true) String code){
        Map<String, Object> data = new HashMap<>();
        int c = commonService.checkCode(code);
        if(c == 0){
            data.put("code",412);
            data.put("user-id",-1);
        }
        else {
            User user = userService.login(username,Base64Util.encodeData(password));
            if(user == null){
                data.put("code",401);
                data.put("user-id",-1);
            }
            if(!user.image.equals("")){
                user.setImageByte(Base64.getDecoder().decode(user.image));
            }
            data.put("code",200);
            data.put("data",user);

        }
        return data;
    }
    @PutMapping("/register")
    public Map<String, Object> register(@RequestParam(name = "name",required = true) String name,
                                        @RequestParam(name = "username",required = true) String username,
                                        @RequestParam(name = "password",required = true) String password,
                                        @RequestParam(name = "phone",required = true) String phone,
                                        @RequestParam(name = "introduce",required = true) String introduce,
                                        @RequestParam(name = "image",required = false) MultipartFile image,
                                        @RequestParam(name = "role",required = false) int role,
                                        @RequestParam(name = "code",required = true) String code) throws IOException {
        Map<String, Object> data = new HashMap<>();
        int c = commonService.checkCode(code);
        if(c == 0){
            data.put("code",412);
            data.put("user-id",-1);
        }
        else {
            User u = new User();
            u.setName(name);
            u.setUsername(username);
            u.setPassword(Base64Util.encodeData(password));
            u.setPhone(phone);
            u.setIntroduce(introduce);
            if(image!=null){
                String base64Str = Base64.getEncoder().encodeToString(image.getBytes());
                u.setImage(base64Str);
            }
            u.setRole(role);
            if(userService.register(u)!=1){
                data.put("code",413);
            }
            data.put("code",200);
        }
        return data;
    }
    @GetMapping()
    public Map<String, Object> getUsersList(@RequestParam(name = "page",defaultValue = "1", required = false) int page,
                                           @RequestParam(name = "size",defaultValue = "10", required = false) int size,
                                       @RequestParam(name = "user_id",required = true) int user_id){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        PageInfo<User> queryResult = userService.findAllUserByPageS(page, size);
        data.put("code",200);
        data.put("data",queryResult);
        return data;
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable(name = "id") int id,
                                          @RequestParam(name = "user_id",required = true)int user_id){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,0)==0){
            data.put("code",413);
            return data;
        }
        if(userService.delete(id)!=1){
            data.put("code",413);
        }
        data.put("code",200);
        return data;
    }

    @GetMapping("/ping")
    public Map<String, Object> pingUser(){
        Map<String, Object> data = new HashMap<>();
        int count = userService.pingUser();
        data.put("User Count",count);
        return data;
    }
    @GetMapping(value = "/userimage",produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getCode() throws IOException {
        User user = userService.login("aaa",Base64Util.encodeData("pas"));
        byte[] bytes =null;
        if(user != null){
            bytes = Base64.getDecoder().decode(user.image);
        }
        return bytes;
    }
}

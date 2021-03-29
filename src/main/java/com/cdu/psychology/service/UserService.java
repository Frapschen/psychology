package com.cdu.psychology.service;

import com.cdu.psychology.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    int pingUser();
    User login(String u, String p);
    int register(User u);
    int delete(int id);
    PageInfo<User> findAllUserByPageS(int pageNum, int pageSize);
    int checkRole(int user_id,int role);
}

package com.cdu.psychology.service;

import com.cdu.psychology.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    int pingUser();
    User login(String u, String p);
    int register(User u);
    int delete(int id);
}

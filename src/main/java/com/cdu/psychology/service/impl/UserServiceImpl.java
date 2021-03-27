package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.UserDao;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service()
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int pingUser() {
        List<User> u =userDao.getUserCount();
        return u.size();
    }
}

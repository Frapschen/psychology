package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.UserDao;
import com.cdu.psychology.entity.Code;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.CommonService;
import com.cdu.psychology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service()
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommonService commonService;

    @Override
    public int pingUser() {
        List<User> u =userDao.getUserCount();
        return u.size();
    }

    @Override
    public User login(String u, String p) {
        return  userDao.getUserByLogin(u,p);
    }

    @Override
    public int register(User u) {
        return userDao.register(u);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }
}

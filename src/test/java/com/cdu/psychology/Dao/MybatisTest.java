package com.cdu.psychology.Dao;

import com.cdu.psychology.dao.UserDao;
import com.cdu.psychology.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MybatisTest {
    @Autowired
    private UserDao userDao;

    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public void connectTest(){
        List<User>  u =userDao.getUserCount();
        logger.info("用户表中有："+u.size()+"条数据！");
    }
}

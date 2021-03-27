package com.cdu.psychology.dao;

import com.cdu.psychology.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  UserDao {

    List<User> getUser();
    List<User> getUserCount();
}

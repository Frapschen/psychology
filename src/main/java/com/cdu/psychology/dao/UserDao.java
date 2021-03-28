package com.cdu.psychology.dao;

import com.cdu.psychology.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface  UserDao {

    @Select("select * from user")
    List<User> getUser();
    List<User> getUserCount();
    User getUserByLogin(String u, String p);
    int register(User u);
    int delete(int id);
}

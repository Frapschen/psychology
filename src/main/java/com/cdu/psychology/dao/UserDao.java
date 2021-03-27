package com.cdu.psychology.dao;

import com.cdu.psychology.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface  UserDao {

    List<User> getUser();
    List<User> getUserCount();
}

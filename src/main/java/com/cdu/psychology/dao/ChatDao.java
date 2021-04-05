package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Chat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatDao {

    @Select("select id,name,introduce,room from chat")
    List<Chat> getChats();

    @Select("select count(*) from chat where room = #{room} ")
    int getRoom(String room);

    @Insert("insert into chat (name,introduce,room) values (#{name},#{introduce},#{room}) ")
    int putChat(Chat chat);

    @Delete("delete from chat where id=#{id}")
    int deleteChat(int id);
}

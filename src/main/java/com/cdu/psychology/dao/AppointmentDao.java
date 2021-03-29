package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Appointment;
import com.cdu.psychology.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AppointmentDao {
    @Insert("insert into appointment (time,user_id,teacher_id) values (#{time},#{user_id},#{teacher_id})")
    int setAppointment(int user_id,int teacher_id, String time);

    @Select("select * from user where id in (select teacher_id from appointment where user_id=#{user_id})")
    List<User> getAppointmentWithUserId(int user_id);

    @Select("select * from user where id in (select user_id from appointment where teacher_id=#{teacher_id})")
    List<User> getAppointmentWithTeacherId(int teacher_id);

    @Update("update appointment set finished =1 where teacher_id=#{teacher_id} and id=#{appointment_id}")
    int updateAppointmentTeacherId(int appointment_id,int teacher_id);
}

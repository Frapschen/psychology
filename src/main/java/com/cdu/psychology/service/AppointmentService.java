package com.cdu.psychology.service;

import com.cdu.psychology.entity.Appointment;
import com.cdu.psychology.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppointmentService {
    PageInfo<User> findAppointmentServiceByPage(int pageNum, int pageSize);
    int putAppointment(int user_id, int teacher_id);
    PageInfo<User> getAppointmentWithUserId(int user_id,int page, int size);
    PageInfo<User> getAppointmentWithTeacherId(int teacher_id,int page,int size);
    int finishAppointment(int appointment_id,int teacher_id);
}

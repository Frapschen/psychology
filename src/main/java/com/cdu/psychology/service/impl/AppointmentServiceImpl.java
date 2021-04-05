package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.AppointmentDao;
import com.cdu.psychology.dao.UserDao;
import com.cdu.psychology.entity.Appointment;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.AppointmentService;
import com.cdu.psychology.service.UserService;
import com.cdu.psychology.utils.TimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public PageInfo<User> findAppointmentServiceByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userDao.getUserByRole(2);
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
        return pageInfo;
    }

    @Override
    public int putAppointment(int user_id, int teacher_id) {
        String time = TimeUtil.getTimestamp();
        int status = appointmentDao.setAppointment(user_id,teacher_id,time);
        return status;
    }

    @Override
    public PageInfo<User> getAppointmentWithUserId(int user_id,int page,int size) {
        PageHelper.startPage(page, size);
        List<User> lists = appointmentDao.getAppointmentWithUserId(user_id);
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
        return pageInfo;
    }

    @Override
    public PageInfo<User> getAppointmentWithTeacherId(int teacher_id,int page,int size) {
        PageHelper.startPage(page, size);
        List<User> lists = appointmentDao.getAppointmentWithTeacherId(teacher_id);
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
        return pageInfo;
    }

    @Override
    public int finishAppointment(int appointment_id, int teacher_id) {
        return appointmentDao.updateAppointmentTeacherId(appointment_id, teacher_id);
    }
}

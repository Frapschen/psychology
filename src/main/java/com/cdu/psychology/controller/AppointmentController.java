package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Appointment;
import com.cdu.psychology.entity.User;
import com.cdu.psychology.service.AppointmentService;
import com.cdu.psychology.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Map<String, Object> getAppointmentList(@RequestParam(defaultValue = "1", required = false) int page,
                                             @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        data.put("code",200);
        PageInfo<User> queryResult = appointmentService.findAppointmentServiceByPage(page, size);
        data.put("data",queryResult);
        return data;
    }
    @PutMapping("/{user_id}/{teacher_id}")
    public Map<String, Object> putAppointment(@PathVariable(required = true) int user_id,
                                              @PathVariable(required = true) int teacher_id){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,1)==0 || userService.checkRole(teacher_id,2)==0){
            data.put("code",413);
            return data;
        }
        if(appointmentService.putAppointment(user_id, teacher_id)==0){
            data.put("code",413);
        }
        data.put("code",200);
        return data;
    }
    //select * from user join appointment on user.id=appointment.user_id where user.id=6;
    @GetMapping("/{user_id}/user")
    public Map<String, Object> getAppointmentsByUser(@PathVariable(required = true) int user_id,
                                                @RequestParam(defaultValue = "1", required = false) int page,
                                                @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(user_id,1)==0){
            data.put("code",413);
            return data;
        }
        PageInfo<User> userPageInfo = appointmentService.getAppointmentWithUserId(user_id,page,size);
        data.put("code",200);
        data.put("users",userPageInfo);
        return data;
    }
    @GetMapping("{teacher_id}/teacher")
    public Map<String, Object> getAppointmentsByTeacher(@PathVariable(required = true) int teacher_id,
                                                        @RequestParam(defaultValue = "1", required = false) int page,
                                                        @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        if(userService.checkRole(teacher_id,2)==0){
            data.put("code",413);
            return data;
        }
        PageInfo<User> userPageInfo = appointmentService.getAppointmentWithTeacherId(teacher_id,page,size);
        data.put("code",200);
        data.put("users",userPageInfo);
        return data;
    }
    @PostMapping("/{appointment_id}/finish/{teacher_id}")
    public Map<String, Object> finishAppointment(@PathVariable(required = true) int appointment_id,
                                                 @PathVariable(required = true) int teacher_id){
        Map<String, Object> data = new HashMap<>();
        if(appointmentService.finishAppointment(appointment_id, teacher_id)==0){
            data.put("code",413);

        }
        data.put("code",200);
        return data;
    }
}

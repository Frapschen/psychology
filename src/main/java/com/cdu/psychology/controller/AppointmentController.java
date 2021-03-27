package com.cdu.psychology.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

    @GetMapping("/list")
    public Map<String, Object> getAppointmentList(@RequestParam(defaultValue = "0", required = false) int page,
                                                  @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        data.put("message page",page);
        data.put("message size",size);
        return data;
    }
    @GetMapping("/{user_id}/{teacher_id}")
    public Map<String, Object> putAppointment(@PathVariable(required = true) int user_id,
                                              @PathVariable(required = true) int teacher_id){
        Map<String, Object> data = new HashMap<>();
        data.put("message user_id",user_id);
        data.put("message teacher_id",teacher_id);
        return data;
    }

    @GetMapping("/{user_id}/user")
    public Map<String, Object> getAppointmentsByUser(@PathVariable(required = true) int user_id,
                                                @RequestParam(defaultValue = "0", required = false) int page,
                                                @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        data.put("message page",page);
        data.put("message size",size);
        data.put("message user_id",user_id);
        return data;
    }
    @GetMapping("{teacher_id}/teacher")
    public Map<String, Object> getAppointmentsByTeacher(@PathVariable(required = true) int teacher_id,
                                                        @RequestParam(defaultValue = "0", required = false) int page,
                                                        @RequestParam(defaultValue = "10", required = false) int size){
        Map<String, Object> data = new HashMap<>();
        data.put("message page",page);
        data.put("message size",size);
        data.put("message teacher_id",teacher_id);
        return data;
    }
    @PostMapping("/{appointment_id}/finished/{teacher_id}")
    public Map<String, Object> finishAppointment(@PathVariable(required = true) int appointment_id,
                                                 @PathVariable(required = true) int teacher_id){
        Map<String, Object> data = new HashMap<>();
        data.put("message appointment_id",appointment_id);
        data.put("message teacher_id",teacher_id);
        return data;
    }
}

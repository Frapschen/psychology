package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class Appointment {
    @Getter @Setter public String name;
    @Getter @Setter public String username;
    @Getter @Setter public String introduce;
    @Getter @Setter public String phone;
    @Getter @Setter public String image;
    @Getter @Setter public byte[] imageByte;
    @Getter @Setter public String time;
    @Getter @Setter public int finished;
}

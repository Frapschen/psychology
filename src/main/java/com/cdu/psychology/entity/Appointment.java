package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class Appointment {
    @Getter @Setter public int id;
    @Getter @Setter public String time;
    @Getter @Setter public int user_id;
    @Getter @Setter public int teacher_id;
    @Getter @Setter public int finished;
}

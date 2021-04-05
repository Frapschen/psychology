package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class Chat {
    @Getter @Setter public int id;
    @Getter @Setter public String name;
    @Getter @Setter public String introduce;
    @Getter @Setter public String room;
}

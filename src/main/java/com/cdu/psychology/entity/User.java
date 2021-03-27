package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter @Setter public int id;
    @Getter @Setter public String username;
    @Getter @Setter public String password;
    @Getter @Setter public String phone;
    @Getter @Setter public String introduce;
    @Getter @Setter public String context;
    @Getter @Setter public Byte[] image;
    @Getter @Setter public int role;
}

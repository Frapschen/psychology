package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter @Setter public int id;
    @Getter @Setter public String username;
    @Getter @Setter public String password;
    @Getter @Setter public int role;
}

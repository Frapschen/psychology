package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class Article {
    @Getter @Setter public int id;
    @Getter @Setter public String name;
    @Getter @Setter public String introduce;
    @Getter @Setter public String context;
    @Getter @Setter public String create_time;
}

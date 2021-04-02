package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Article {
    @Getter @Setter public int id;
    @Getter @Setter public String title;
    @Getter @Setter public String introduce;
    @Getter @Setter public String content;
    @Getter @Setter public String created_time;
    @Getter @Setter public List<Comment> comments;
}

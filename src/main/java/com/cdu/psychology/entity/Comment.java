package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class Comment {
    @Getter @Setter public int id;
    @Getter @Setter public String contxt;
    @Getter @Setter public int user_id;
    @Getter @Setter public int article_id;
}

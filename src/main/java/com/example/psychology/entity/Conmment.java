package com.example.psychology.entity;

import lombok.Getter;
import lombok.Setter;

public class Conmment {
    @Getter @Setter public int id;
    @Getter @Setter public String contxt;
    @Getter @Setter public int user_id;
    @Getter @Setter public int article_id;
}

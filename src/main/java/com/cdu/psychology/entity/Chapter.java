package com.cdu.psychology.entity;
import lombok.Getter;
import lombok.Setter;
public class Chapter {
    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private String time;
    @Getter @Setter private String location;
    @Getter @Setter private String character;
    @Getter @Setter private int show_id;
    @Getter @Setter private String next;
}

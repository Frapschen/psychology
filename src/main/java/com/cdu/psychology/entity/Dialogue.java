package com.cdu.psychology.entity;
import lombok.Getter;
import lombok.Setter;
public class Dialogue {
    @Getter @Setter private String id;
    @Getter @Setter private String character;
    @Getter @Setter private String content;
    @Getter @Setter private int is_lead;
    @Getter @Setter private int four_content;
    @Getter @Setter private String chapter_id;
    @Getter @Setter private String next;
}

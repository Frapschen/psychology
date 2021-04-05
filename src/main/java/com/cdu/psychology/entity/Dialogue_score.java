package com.cdu.psychology.entity;
import lombok.Getter;
import lombok.Setter;
public class Dialogue_score {
    @Getter @Setter private int id;
    @Getter @Setter private String content1;
    @Getter @Setter private String content2;
    @Getter @Setter private String content3;
    @Getter @Setter private String content4;

    @Getter @Setter private int score1;
    @Getter @Setter private int score2;
    @Getter @Setter private int score3;
    @Getter @Setter private int score4;
    @Getter @Setter private String chapter_id;
}

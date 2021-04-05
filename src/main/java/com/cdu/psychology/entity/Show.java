package com.cdu.psychology.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Show {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private String introduce;
    @Getter @Setter private String image;
    @Getter @Setter private String character;
    @Getter @Setter private String lead;
    @Getter @Setter private List<String> chapters;

    public Show(String name, String introduce, String image, String character, String lead) {
        this.name = name;
        this.introduce = introduce;
        this.image = image;
        this.character = character;
        this.lead = lead;
    }

    public Show() {

    }
}

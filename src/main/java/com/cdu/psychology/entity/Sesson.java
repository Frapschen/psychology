package com.cdu.psychology.entity;

import javax.websocket.Session;
import lombok.Getter;
import lombok.Setter;
public class Sesson {
    @Getter @Setter public String user_id;
    @Getter @Setter public Session session;

    public Sesson(String user_id, Session session) {
        this.user_id = user_id;
        this.session = session;
    }
    public Sesson(){}
}

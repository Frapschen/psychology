package com.cdu.psychology.service;

import com.cdu.psychology.entity.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> getChats();
    int checkRoom(String room);
    int putChat(Chat chat);
    int deleteChat(int id);
    int test();
}

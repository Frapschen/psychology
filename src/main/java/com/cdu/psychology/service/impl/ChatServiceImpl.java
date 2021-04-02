package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.ChatDao;
import com.cdu.psychology.entity.Chat;
import com.cdu.psychology.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatDao chatDao;
    @Override
    public List<Chat> getChats() {
        return chatDao.getChats();
    }

    @Override
    public int checkRoom(String room) {
        return chatDao.getRoom(room);
    }

    @Override
    public int putChat(Chat chat) {
        return chatDao.putChat(chat);
    }

    @Override
    public int deleteChat(int id) {
        return chatDao.deleteChat(id);
    }
    @Override
    public int test(){
        return 123;
    }
}

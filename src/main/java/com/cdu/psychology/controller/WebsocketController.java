package com.cdu.psychology.controller;

import com.cdu.psychology.service.ChatService;
import com.cdu.psychology.service.impl.ChatServiceImpl;
import com.cdu.psychology.utils.WebsocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint( value = "/v1/chat/{room}/room/{user_id}" )
public class WebsocketController {

//    private ChatService chatService;

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebsocketController.applicationContext = applicationContext;
    }

    /**
     * 连接事件，加入注解
     * @param user_id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam( value = "user_id" ) String user_id,
                       @PathParam( value = "room" ) String room,
                       Session session ) {
        String message ="[系统通知] 用户 "+"[" + user_id + "]加入聊天室！！";
        //注入
        int code =0;
        ChatService chatService = applicationContext.getBean(ChatServiceImpl.class);
        code = chatService.checkRoom(room);
        if(code==0){
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 添加到session的映射关系中
        WebsocketUtil.addSession (user_id, room, session );
        // 广播通知，某用户上线了
        WebsocketUtil.sendMessageForAll (room, message);
    }

    /**
     * 连接事件，加入注解
     * 用户断开链接
     * @param user_id
     * @param session
     */
    @OnClose
    public void onClose(@PathParam  ( value = "user_id" ) String user_id,
                        @PathParam( value = "room" ) String room,
                        Session session ) {
        String message ="[系统通知] 用户 "+"[" + user_id + "]退出了聊天室...";

        // 删除映射关系
        WebsocketUtil.removeSession (user_id, room);
        // 广播通知，用户下线了
        WebsocketUtil.sendMessageForAll (room,message);
    }

    /**
     * 当接收到用户上传的消息
     * @param user_id
     * @param session
     */
    @OnMessage
    public void onMessage(@PathParam  ( value = "user_id" ) String user_id,
                          @PathParam( value = "room" ) String room,
                          Session session ,
                          String message) {

        String msg ="[聊天信息] 用户 "+"[" + user_id + "]:"+message;
        // 直接广播
        WebsocketUtil.sendMessageForAll (room,msg);
    }

    /**
     * 处理用户活连接异常
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}

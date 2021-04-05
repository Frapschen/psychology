package com.cdu.psychology.utils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebsocketUtil {
    /**
     * 记录当前在线的Session
     */
    private static final Map<String, List<com.cdu.psychology.entity.Sesson>> ONLINE_SESSION = new ConcurrentHashMap<>();

    /**
     * 添加session
     * @param room
     * @param session
     */
    public static void addSession(String user_id ,String room, Session session){
        // 遍历所有的room 删除其他的房间用户，加入当前room
        List<com.cdu.psychology.entity.Sesson> sessionList =ONLINE_SESSION.get(room);
        if (sessionList==null){
            List<com.cdu.psychology.entity.Sesson> sessions = new ArrayList();
            sessions.add(new com.cdu.psychology.entity.Sesson(user_id,session));
            ONLINE_SESSION.put(room,sessions);
        } else {
            sessionList.add(new com.cdu.psychology.entity.Sesson(user_id,session));
        }
    }

    /**
     * 关闭session
     * @param user_id
     */
    public static void removeSession(String user_id,String room){
        List<com.cdu.psychology.entity.Sesson> sessionList =ONLINE_SESSION.get(room);
        for(int i=0;i<sessionList.size();i++){
            com.cdu.psychology.entity.Sesson sesson = sessionList.get(i);
            if(sesson.user_id.equals(user_id)){
                sessionList.remove(i);
                break;
            }
        }
    }

    /**
     * 给单个用户推送消息
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message){
        if(session == null){
            return;
        }

        // 同步
        RemoteEndpoint.Async async  = session.getAsyncRemote ();
        async.sendText ( message );
    }

    /**
     * 向所有在线人发送消息
     * @param message
     */
    public static void sendMessageForAll(String room,String message) {
        List<com.cdu.psychology.entity.Sesson> sessionList =ONLINE_SESSION.get(room);

        //jdk8 新方法
        sessionList.forEach((session) -> sendMessage(session.session, message));
    }
}

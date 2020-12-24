package com.example.chatonline.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Service.MessageServiceImpl;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketController {

    private MessageServiceImpl messageServiceImpl = new MessageServiceImpl();

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, WebSocketController> webSocketMap  = new ConcurrentHashMap<String, WebSocketController>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session WebSocketsession;
    //当前发消息的人员编号
    private String userId = "";

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.WebSocketsession = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }
    }
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        //给指定的人发消息
        sendToUser(jsonObject.toJavaObject(Message.class));
        //sendAll(message);
    }


    /**
     * 给指定的人发送消息
     *
     * @param message 消息对象
     */
    public void sendToUser(Message message) {
        String reviceUserid = message.getReciveid();
        String sendMessage = message.getMessagetext();
        Message inmessage = new Message();
        inmessage.setReciveid(reviceUserid);
        inmessage.setTexttype(message.getTexttype());
        inmessage.setSendid(userId);
        inmessage.setMessagetext(sendMessage);
        inmessage.setSendtime(new Date());
        messageServiceImpl.InsertMessage(inmessage);
        try {
            if (webSocketMap.get(reviceUserid) != null) {
                webSocketMap.get(reviceUserid).sendMessage(userId+"|"+sendMessage);
            }else{
                webSocketMap.get(userId).sendMessage("0"+"|"+"当前用户不在线");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.WebSocketsession.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

}

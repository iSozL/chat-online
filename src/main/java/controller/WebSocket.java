package controller;

import dao.MessageDao;
import dao.MessageDaoImpl;
import dao.OnlineUserDao;
import dao.OnlineUserDaoImpl;
import net.sf.json.JSONObject;
import service.RecentMessageDao;
import service.RecentMessageDaoImpl;
import utils.JsonHandle;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket
 */
@ServerEndpoint(value = "/websocket/{userName}")
public class WebSocket {
    private String userName;//用户名
    private Session session;//session

    /**
     * 用线程安全的Map集合，将所有连接成功的websocket实例存储在一起，键值为用户名
     */
    private static Map<String, WebSocket> webSocketMap = new ConcurrentHashMap<String, WebSocket>();

    /**
     * 返回Map集合
     * @return
     */
    private static Map<String, WebSocket> getWebSocketMap() {
        return webSocketMap;
    }

    /**
     * websocket连接成功，将该websocket实例添加至map集合中，并修改用户在线状态
     * 同时接受用户离线状态下所有的未读消息，以json格式返回至客户端
     *
     * @param userName
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("userName") String userName, Session session) throws IOException {
        this.userName = userName;
        this.session = session;
        // 用用户名来做webSocket通信的唯一标识
        String key = userName;
        webSocketMap.put(key, this);

        OnlineUserDao onlineUserDao = new OnlineUserDaoImpl();
        onlineUserDao.addUser(userName);

        MessageDao messageDao = new MessageDaoImpl();
        for (String data : messageDao.getUnReadMessage(userName)) {
            this.session.getBasicRemote().sendText(data);//返回未读消息至客户端
        }

        System.out.println(userName + "加入连接");
    }

    /**
     * 连接关闭，修改用户在线状态
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(userName);

        OnlineUserDao onlineUserDao = new OnlineUserDaoImpl();
        onlineUserDao.deleteUser(userName);//在线列表将该用户删除
        System.out.println(userName + "退出连接");
    }

    /**
     * 连接发生错误，修改用户在线状态
     *
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();

        OnlineUserDao onlineUserDao = new OnlineUserDaoImpl();
        onlineUserDao.deleteUser(userName);//在线列表将该用户删除
        System.out.println("发生错误，" + userName + "退出连接");
    }

    /**
     * 接受客户端发来的数据，存储到数据库中，同时将消息发送给接收方
     *
     * @param clientData
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String clientData) throws IOException {
        JsonHandle jsonHandle = new JsonHandle(clientData);
        int flag=jsonHandle.getFlag();

        //发送的是消息
        if(flag==0) {
            String receiver = jsonHandle.getReceiver();
            String message = jsonHandle.getMessage();

            String now = getNowTime();
            MessageDao messageDao = new MessageDaoImpl();
            messageDao.storingData(userName, receiver, now, message, 0);//存储消息至数据库，默认为未读

            sendToUser(receiver, message, now);//发送给接收方


            //最新消息列表
            RecentMessageDao recentMessageDao=new RecentMessageDaoImpl();
            if(recentMessageDao.isExists(userName,receiver))
            {
                recentMessageDao.updateMessage(userName,receiver,now,message);
            }
            else
            {
                recentMessageDao.storingMessage(userName,receiver,now,message);
            }
        }
        //发送的是好友申请
        else{
            String receiver = jsonHandle.getReceiver();
            for (WebSocket item : webSocketMap.values()) {
                if (item.userName.equals(receiver)) {
                    JSONObject jsonObject = new JSONObject();//对消息进行封装
                    jsonObject.put("flag", "1");
                    String data = jsonObject.toString();
                    item.session.getAsyncRemote().sendText(data);//异步发送
                }
            }
        }
    }

    /**
     * 向指定用户发送消息
     *
     * @param receiver
     * @param message
     * @param time
     * @throws IOException
     */
    public void sendToUser(String receiver, String message, String time) throws IOException {
        for (WebSocket item : webSocketMap.values()) {
            if (item.userName.equals(receiver)) {
                JSONObject jsonObject = new JSONObject();//对消息进行封装
                jsonObject.put("sender", userName);
                jsonObject.put("receiver", receiver);
                jsonObject.put("message", message);
                jsonObject.put("time", time);
                String data = jsonObject.toString();
                item.session.getAsyncRemote().sendText(data);//异步发送

                MessageDaoImpl messageDao = new MessageDaoImpl();
                messageDao.setStatus(userName, receiver, time, 1);//一旦发送成功，修改数据库中消息状态
            }
        }
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public String getNowTime() {
        Date now = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转化格式
        return f.format(now);
    }
}

package dao;

import java.util.ArrayList;

/**
 * 数据库消息
 */
public interface MessageDao {
    /**
     * 存储消息至数据库
     *
     * @param sender
     * @param receiver
     * @param time
     * @param message
     * @param status
     * @return
     */
    boolean storingData(String sender, String receiver, String time, String message, int status);

    /**
     * 修改消息的状态，是否已读
     *
     * @param sender
     * @param receiver
     * @param time
     * @param status
     * @return
     */
    boolean setStatus(String sender, String receiver, String time, int status);

    /**
     * 返回某用户当前所有未读消息
     *
     * @param receiver
     * @return
     */
    ArrayList<String> getUnReadMessage(String receiver);

    /**
     * 返回两个用户间的聊天记录
     *
     * @param first
     * @param second
     * @return
     */
    ArrayList<String> getChatRecord(String first, String second);
}

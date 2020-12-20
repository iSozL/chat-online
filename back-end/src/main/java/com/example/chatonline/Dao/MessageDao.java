package com.example.chatonline.Dao;

import com.example.chatonline.Model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository("messageDao")
public interface MessageDao {
    //插入聊天记录
    void InsertMessage(Message message);
    //查询聊天记录
    List<Message> QueryMessage(Message message);
    //检查是否存在某一验证消息
    public Map<String,Object> FindVerifyMessage(Message message);
    //删除某一验证消息
    public boolean DelVerifyMessage(Message message);
    //添加验证消息
    public boolean AddVerifyMessage(Message message,String note,String groupname);
    //显示验证消息
    public ArrayList<Message> ShowVerifyMessage(String reciveId);
    //处理验证消息
    public boolean HandleVerifyMessage(int type,String userId,String reciveId);
    //显示最新发送消息列表
    public ArrayList<Map<String,Object>> ShowsendLastMessage(String userId);
    //显示最新接受消息列表
    public ArrayList<Map<String,Object>> ShowreciveLastMessage(String userId);

    //删除好友所有历史消息
    public Integer DeleteAllMessage(String userId, String friendId);
}

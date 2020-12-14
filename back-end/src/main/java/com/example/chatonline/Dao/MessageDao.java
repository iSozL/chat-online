package com.example.chatonline.Dao;

import com.example.chatonline.Model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("messageDao")
public interface MessageDao {
    //插入聊天记录
    void InsertMessage(Message message);
    //查询聊天记录
    List<Message> QueryMessage(Message message);
    //检查是否存在某一验证消息
    public boolean FindMessage(Message message);
    //删除某一验证消息
    public boolean DelMessage(Message message);


}

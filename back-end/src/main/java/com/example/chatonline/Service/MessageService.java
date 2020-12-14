package com.example.chatonline.Service;

import com.example.chatonline.Dao.MessageDao;
import com.example.chatonline.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("messageService")
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public void InsertMessage(Message message){
        messageDao.InsertMessage(message);
    }

    public List<Message> LookTwoUserMsg(Message message){
        return messageDao.QueryMessage(message);
    }
    //查询是否存在验证消息
    public boolean FindMessage(Message message)
    {
        return messageDao.FindMessage(message);
    }
    //删除验证消息
    public boolean DelMessage(Message message){
        return messageDao.DelMessage(message);
    }

}

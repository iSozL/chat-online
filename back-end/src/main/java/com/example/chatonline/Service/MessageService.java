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

    public void InsertMessage(Message message){ messageDao.InsertMessage(message); }
    public List<Message> LookTwoUserMsg(Message message){ return messageDao.QueryMessage(message); }

    //查询是否存在验证消息
    public boolean FindVerifyMessage(Message message)
    {
        return messageDao.FindVerifyMessage(message);
    }
    //删除验证消息
    public boolean DelVerifyMessage(Message message){
        return messageDao.DelVerifyMessage(message);
    }
    //添加验证消息
    public boolean  AddVerifyMessage(Message message,String note,String groupname) { return messageDao.AddVerifyMessage(message, note, groupname); }

}

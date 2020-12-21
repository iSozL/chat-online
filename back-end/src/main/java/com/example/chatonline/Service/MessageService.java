package com.example.chatonline.Service;

import com.example.chatonline.Dao.MessageDao;
import com.example.chatonline.Model.Image;
import com.example.chatonline.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("messageService")
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public void InsertMessage(Message message){ messageDao.InsertMessage(message); }
    public List<Message> LookTwoUserMsg(Message message){ return messageDao.QueryMessage(message); }

    //查询某一未处理验证消息
    public Map<String,Object> FindVerifyMessage(Message message)
    {
        return messageDao.FindVerifyMessage(message);
    }
    //删除验证消息
    public boolean DelVerifyMessage(Message message){
        return messageDao.DelVerifyMessage(message);
    }
    //添加验证消息
    public boolean  AddVerifyMessage(Message message,String note,String groupname) { return messageDao.AddVerifyMessage(message, note, groupname); }
    //显示验证消息
    public ArrayList<Message> ShowVerifyMessage(String reciveId) {return messageDao.ShowVerifyMessage(reciveId);}
    //处理验证消息
    public boolean HandleVerifyMessage(int type,String userId,String reciveId){return messageDao.HandleVerifyMessage(type, userId, reciveId);}
    //显示消息列表
    public ArrayList<Map<String,Object>> ShowsendLastMessage(String userId){ return messageDao.ShowsendLastMessage(userId); }
    public ArrayList<Map<String,Object>> ShowreciveLastMessage(String userId){ return messageDao.ShowreciveLastMessage(userId); }
    //删除好友消息记录
    public Integer DeleteAllMessage(String userId, String friendId){
        return messageDao.DeleteAllMessage(userId, friendId);
    }
    //添加好友映像
    public boolean addImage(Message message) { return messageDao.addImage(message);}
    //删除自己发送的好友印象
    public boolean DelImage(Message message){
        return messageDao.DelImage(message);
    }
    //删除接收的好友印象
    public boolean DelReceiveImage(Message message){
        return messageDao.DelReceiveImage(message);
    }
    //显示已接收的好友印象
    public ArrayList<Image> ShowImage(String reciveId){return messageDao.ShowImage(reciveId);}
    //修改好友备注
    public boolean ChangMark(String userId,String note) { return messageDao.ChangMark(userId,note); }
    //得到所选用户权限信息
    public Integer GetImageMark(String userId){
        return messageDao.GetImageMark(userId);
    }
}

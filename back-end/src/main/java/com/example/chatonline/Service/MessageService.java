package com.example.chatonline.Service;

import com.example.chatonline.Dao.MessageDao;
import com.example.chatonline.Model.Image;
import com.example.chatonline.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    //删除未处理验证消息
    public boolean DelVerifyMessage(Message message){
        return messageDao.DelVerifyMessage(message);
    }
    //添加未处理验证消息
    public boolean  AddVerifyMessage(Message message,String note,String groupname) { return messageDao.AddVerifyMessage(message, note, groupname); }
    //显示验证消息
    public ArrayList<Message> ShowVerifyMessage(String reciveId) {return messageDao.ShowVerifyMessage(reciveId);}

    //处理验证消息
    public boolean HandleVerifyMessage(int type,String userId,String reciveId){return messageDao.HandleVerifyMessage(type, userId, reciveId);}
    //拒绝好友请求
    public boolean Refuseadd(String userId, String sendId, Date sendtime){return messageDao.Refuseadd(userId, sendId, sendtime);}
    //删除已处理或者未处理消息验证消息
    public boolean DeleteVerifyMessage(String userId, String sendId, Date sendtime){return messageDao.DeleteVerifyMessage(userId, sendId, sendtime);}
    //显示消息列表
    public ArrayList<Map<String,Object>> ShowsendLastMessage(String userId){ return messageDao.ShowsendLastMessage(userId); }
    public ArrayList<Map<String,Object>> ShowreciveLastMessage(String userId){ return messageDao.ShowreciveLastMessage(userId); }
    //删除好友消息记录
    public Integer DeleteAllMessage(String userId, String friendId){
        return messageDao.DeleteAllMessage(userId, friendId);
    }
    //添加好友映像
    public boolean addImage(Image message) { return messageDao.addImage(message);}
    //删除自己发送的好友印象
    public boolean DelImage(Image message){
        return messageDao.DelImage(message);
    }
    //删除接收的好友印象
    public boolean DelReceiveImage(Message message){
        return messageDao.DelReceiveImage(message);
    }
    //显示已接收的好友印象
    public ArrayList<Image> ShowImage(String reciveId){return messageDao.ShowImage(reciveId);}
    //修改好友权限
    public boolean ChangMark(String userId,String note) { return messageDao.ChangMark(userId,note); }
    //得到所选用户权限信息
    public Integer GetImageMark(String userId){
        return messageDao.GetImageMark(userId);
    }
}

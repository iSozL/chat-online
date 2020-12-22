package com.example.chatonline.Dao;

import com.example.chatonline.Model.Image;
import com.example.chatonline.Model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
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
    //删除未处理验证消息
    public boolean DelVerifyMessage(Message message);
    //添加验证消息
    public boolean AddVerifyMessage(Message message,String note,String groupname);
    //显示验证消息
    public ArrayList<Message> ShowVerifyMessage(String reciveId);
    //处理验证消息
    public boolean HandleVerifyMessage(int type,String userId,String reciveId);
    //拒绝添加好友
    public boolean Refuseadd(String userId, String sendId, Date sendtime);
    //删除验证消息
    public boolean DeleteVerifyMessage(String userId, String sendId, Date sendtime);
    //显示最新发送消息列表
    public ArrayList<Map<String,Object>> ShowsendLastMessage(String userId);
    //显示最新接受消息列表
    public ArrayList<Map<String,Object>> ShowreciveLastMessage(String userId);
    //删除好友所有历史消息
    public Integer DeleteAllMessage(String userId, String friendId);
    //添加好友映像
    public boolean addImage(Image message);
    //删除自己发送的好友印象
    public boolean DelImage(Image message);
    //删除接收的的好友印象
    public boolean DelReceiveImage(Message message);
    //显示接受到的好友印象
    public ArrayList<Image> ShowImage(String reciveId);
    //更改印象可见权限
    public boolean ChangMark(String userId,String note);
    //得到所选用户权限信息
    public Integer GetImageMark(String userId);
}


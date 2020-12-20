package com.example.chatonline.Service;


import com.example.chatonline.Dao.UserDao;
import com.example.chatonline.Model.Group;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    //注册
    public boolean register(User user) throws Exception{
        if(userDao.QueryUser(user.getUserId())!=null)
            return false;
        return userDao.Register(user);
    }
    //登录
    public User login(String id,String password){
        return userDao.Login(id,password);
    }
    //查询用户
    public User Query(String  id) {return userDao.QueryUser(id);}
    //查找是否为好友关系
    public Boolean FindRelation(String id, String friendId)
    {
        return userDao.FindRelation(id,friendId);
    }
    //查询用户所有好友
    public ArrayList<Map<String,Object>> GroupFriends(String userId) { return userDao.GroupFriends(userId); }
    //查询分组
    public ArrayList<Group> ShowGroup(String id)
    {
        return userDao.ShowGroup(id);
    }
    //删除分组
    public boolean DelGroup(String userId,String groupname){return userDao.DelGroup(userId,groupname); }
    //获得默认分组名
    public String getDefGroupname(String userId){return userDao.getDefGroupname(userId);}
    //获得用户某一分组下的所有好友Id
    public ArrayList<Map<String,Object>> getFriendsId(String userId,String groupname){return userDao.getFriendsId(userId,groupname);}
    //新建分组
    public boolean CreatGroup(String id,String groupname,int type){return userDao.CreatGroup(id, groupname,type);}
    //分组人数+1
    public boolean AddGroupNum(String id,String groupname){return userDao.AddGroupNum(id, groupname);}
    //好友移动
    public boolean GroupMove(String userId,String friendId,String postGroupname){ return userDao.GroupMove(userId,friendId,postGroupname);}
    //分组人数减少
    public boolean preGroupnum(String userId,String preGroupname){ return userDao.preGroupnum(userId,preGroupname);}
    //分组人数增加
    public boolean postGroupnum(String userId,String postGroupname){ return userDao.postGroupnum(userId,postGroupname);}
//    public boolean AddGroupNum(String id,String groupname){return userDao.AddGroupNum(id, groupname);}
//    //添加好友
//    public boolean AddFriend(String userId,String friendId,String note,String groupname) {return userDao.AddFriend(userId, friendId, note, groupname);}
//    //好友移动
    public Integer AddFriend(String userId,String friendId,String noteA,String groupnameA,String noteB,String groupnameB){
        return userDao.AddFriend(userId, friendId, noteA, groupnameA, noteB, groupnameB);
    }
    //编辑个人资料
    public boolean UpdateInfo(String userId, String nickname, String sex, Date birth, String signature, String address,String phone){return userDao.UpdateInfo(userId, nickname, sex, birth, signature, address,phone);}
    //显示个人资料
    public User ShowInfo(String userId){return userDao.ShowInfo(userId);}
    //删除好友关系
    public boolean DeleteRelation(String userId, String friendId){
        return userDao.DeleteRelation(userId, friendId);
    }
}

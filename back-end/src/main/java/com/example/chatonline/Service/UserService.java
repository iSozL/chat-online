package com.example.chatonline.Service;


import com.example.chatonline.Dao.UserDao;
import com.example.chatonline.Model.Group;
import com.example.chatonline.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    //查询某一分组下所有好友
    public ArrayList<Map<String,Object>> FindGroupFriends(String userId, String groupname) { return userDao.FindGroupFriends(userId,groupname); }
    //查询分组
    public ArrayList<Group> ShowGroup(String id)
    {
        return userDao.ShowGroup(id);
    }
    //新建分组
    public boolean CreatGroup(String id,String groupname){return userDao.CreatGroup(id, groupname);}
    //分组人数+1
    public boolean AddGroupNum(String id,String groupname){return userDao.AddGroupNum(id, groupname);}
    //添加好友
    public boolean AddFriend(String userId,String friendId,String note,String groupname) {return userDao.AddFriend(userId, friendId, note, groupname);}
    //好友移动
    public boolean groupMove(String userId,String friendId,String postGroupname){ return userDao.groupMove(userId,friendId,postGroupname);}
    //分组人数减少
    public boolean preGroupnum(String userId,String preGroupname){ return userDao.preGroupnum(userId,preGroupname);}
    //分组人数增加
    public boolean postGroupnum(String userId,String postGroupname){ return userDao.postGroupnum(userId,postGroupname);}
}

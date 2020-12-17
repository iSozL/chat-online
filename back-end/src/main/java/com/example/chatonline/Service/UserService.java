package com.example.chatonline.Service;


import com.example.chatonline.Dao.UserDao;
import com.example.chatonline.Model.Group;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
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
    //查找好友
    public User FindFriend(String id)
    {
        return userDao.FindFriend(id);
    }
    //查询某一分组下所有好友
    public ArrayList<User> FindGroupFriends(String id,String groupname) { return userDao.FindGroupFriends(id, groupname); }
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

}

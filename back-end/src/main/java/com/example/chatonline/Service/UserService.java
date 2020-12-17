package com.example.chatonline.Service;


import com.example.chatonline.Dao.UserDao;
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
    //查询好友分组
    public ArrayList<User> FindGroupFriends(String id,String groupname) { return userDao.FindGroupFriends(id, groupname); }
    //查询分组
    public ArrayList<String> ShowGroup(String id)
    {
        return userDao.ShowGroup(id);
    }
}

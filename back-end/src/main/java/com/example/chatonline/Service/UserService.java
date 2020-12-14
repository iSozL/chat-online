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


    public boolean register(User user) throws Exception{
        if(userDao.QueryUser(user.getUserId())!=null)
            return false;
        return userDao.Register(user);
    }

    public User login(String id,String password){
        return userDao.Login(id,password);
    }
    public User Query(String  id) {return userDao.QueryUser(id);}
    public User FindFriend(String id)
    {
        return userDao.FindFriend(id);
    }

    public ArrayList<User> FindGroupFriends(String id,String groupname)
    {
        return userDao.FindGroupFriends(id, groupname);
    }
    public ArrayList<String> ShowGroup(String id)
    {
        return userDao.ShowGroup(id);
    }
    public boolean  AddFriend(Message message,String note,String groupname)
    {
        return userDao.AddFriend(message, note, groupname);
    }




}

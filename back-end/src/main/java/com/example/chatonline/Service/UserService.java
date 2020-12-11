package com.example.chatonline.Service;


import com.example.chatonline.Dao.UserDao;
import com.example.chatonline.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;


    public boolean register(User user) throws Exception{
        if(userDao.Query(user.getUserId())!=null)
            return false;
        return userDao.Register(user);
    }

    public User login(int id,String password){
        return userDao.Login(id,password);
    }


}

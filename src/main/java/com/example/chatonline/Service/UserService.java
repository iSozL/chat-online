package com.example.chatonline.Service;

import com.example.chatonline.Dao.UserDao;
import com.example.chatonline.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDaoImpl;

    public User login(Connection con,User user) throws Exception {
        return userDaoImpl.Login(con,user);
    }
    public boolean register(Connection con,User user) throws Exception{
        return userDaoImpl.Register(con,user);

    }

}

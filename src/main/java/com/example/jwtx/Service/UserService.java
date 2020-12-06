package com.example.jwtx.Service;

import com.example.jwtx.Dao.Impl.UserDaoImpl;
import com.example.jwtx.Dao.UserDao;
import com.example.jwtx.Model.User;
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

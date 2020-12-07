package com.example.jwtx.Dao;

import com.example.jwtx.Model.User;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.sql.Connection;

public interface UserDao {
    public User Login(Connection con, User user) throws Exception;
    public Boolean Register(Connection con, User user) throws Exception;
}

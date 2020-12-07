package com.example.chatonline.Dao;

import com.example.chatonline.Model.User;

import java.sql.Connection;

public interface UserDao {
    public User Login(Connection con, User user) throws Exception;
    public Boolean Register(Connection con, User user) throws Exception;
}

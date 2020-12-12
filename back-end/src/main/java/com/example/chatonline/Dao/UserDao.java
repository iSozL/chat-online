package com.example.chatonline.Dao;

import com.example.chatonline.Model.User;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.Map;

@Mapper
@Repository("userDao")
public interface UserDao {
    public User Login(int id,String password);
    public User Query(int id);
    public Boolean Register(User user);
}

package com.example.chatonline.Dao;

import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import com.sun.org.apache.xpath.internal.objects.XObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

@Mapper
@Repository("userDao")
public interface UserDao {
    public User Login(String id,String password);
    public User QueryUser(String  id);
    public Boolean Register(User user);
    public ArrayList<User> FindGroupFriends(String id,String groupname);
    public User FindFriend(String id);
    public ArrayList<String> ShowGroup(String id);
    public boolean AddFriend(Message message,String note,String groupname);


}

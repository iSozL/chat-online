package com.example.chatonline.Dao;

import com.example.chatonline.Model.Group;
import com.example.chatonline.Model.Message;
import com.example.chatonline.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Mapper
@Repository("userDao")
public interface UserDao {
    public User Login(String id,String password);
    public User QueryUser(String  id);
    public Boolean Register(User user);
    public ArrayList<Map<String,Object>> GroupFriends(String userId);
    public Boolean FindRelation(String userId,String friendId);
    public ArrayList<Group> ShowGroup(String id);
    public boolean CreatGroup(String id,String groupname);
    public boolean AddGroupNum(String id,String groupname);
    public boolean groupMove(String userId,String friendId,String postGroupname);
    public boolean preGroupnum(String userId,String preGroupname);
    public boolean postGroupnum(String userId,String postGroupname);
    public Integer AddFriend(String userId,String friendId,String noteA,String groupnameA,String noteB,String groupnameB);






}

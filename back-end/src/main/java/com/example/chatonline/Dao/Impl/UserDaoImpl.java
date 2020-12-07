package com.example.chatonline.Dao.Impl;

import com.example.chatonline.Dao.UserDao;
import com.example.chatonline.Model.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Override
    public User Login(Connection con, User user) throws Exception{
        User reuser = null;
        String sql = "select * from user where userId=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,user.getUserId());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next())
        {
            reuser = new User();
            reuser.setUserId(rs.getInt("userId"));
            reuser.setNickname(rs.getString("nickname"));
            reuser.setPassword(rs.getString("password"));
        }
        return reuser;
    }

    @Override
    public Boolean Register(Connection con, User user) throws Exception {
        String sql = "select * from user where userId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,user.getUserId());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next())
           return false;
        String sql2 = "insert into user value(?,?,?)";
        pstmt = con.prepareStatement(sql2);
        pstmt.setInt(1,user.getUserId());
        pstmt.setString(2,user.getNickname());
        pstmt.setString(3,user.getPassword());
        pstmt.executeUpdate();
        return true;
    }


}

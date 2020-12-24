package dao;

import utils.DBUtil;

import java.sql.*;

public class RecentMessageDaoImpl implements RecentMessageDao {
    @Override
    public boolean storingMessage(String sendId, String receiveId, String sendtime, String messagetxt) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;
        conn = DBUtil.getConnection();

        String sql = "insert into lastfriendmessage(sendId,reciveId,sendtime,messagetxt) values(?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, sendId);
            pst.setString(2, receiveId);
            pst.setString(3, sendtime);
            pst.setString(4, messagetxt);
            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public boolean isExists(String sendId, String receiveId) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = DBUtil.getConnection();

        String sql = "select * from lastfriendmessage where sendId=? and reciveId=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,sendId);
            pst.setString(2,receiveId);
            rs = pst.executeQuery();
            while (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return flag;
    }

    @Override
    public boolean updateMessage(String sendId, String receiveId, String sendtime, String messagetxt) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;
        conn = DBUtil.getConnection();

        String sql = "update lastfriendmessage set sendtime=?,messagetxt=? where sendId=? and reciveId=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, sendtime);
            pst.setString(2, messagetxt);
            pst.setString(3, sendId);
            pst.setString(4, receiveId);
            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return flag;
    }
}
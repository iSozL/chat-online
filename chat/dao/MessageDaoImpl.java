package dao;

import net.sf.json.JSONObject;
import utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class MessageDaoImpl implements MessageDao {

    /**
     * 存储消息至数据库中
     *
     * @param sender
     * @param receiver
     * @param time
     * @param message
     * @param status
     * @return
     */
    @Override
    public boolean storingData(String sender, String receiver, String time, String message, int status) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;

        conn = JDBCUtil.getConnection();
        String sql = "insert into message(sender,receiver,time,message,status) values(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, sender);
            pst.setString(2, receiver);
            pst.setString(3, time);
            pst.setString(4, message);
            pst.setInt(5, status);

            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return flag;
    }

    /**
     * 修改消息的状态，是否已读
     *
     * @param sender
     * @param receiver
     * @param time
     * @param status
     * @return
     */
    @Override
    public boolean setStatus(String sender, String receiver, String time, int status) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;

        conn = JDBCUtil.getConnection();
        String sql = "update message set status=? where sender=? and receiver=? and time=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setString(2, sender);
            pst.setString(3, receiver);
            pst.setString(4, time);

            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return flag;
    }

    /**
     * 获取用户当前所有未读消息，以json格式返回
     *
     * @param receiver
     * @return
     */
    @Override
    public ArrayList<String> getUnReadMessage(String receiver) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        conn = JDBCUtil.getConnection();
        String sql = "select * from message where receiver='" + receiver + "'" + " and " + "status=0";
        ArrayList<String> messageArrayList = new ArrayList<String>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                String sender = rs.getString("sender");
                String message = rs.getString("message");
                String time = rs.getString("time");
                jsonObject.put("sender", sender);
                jsonObject.put("receiver", receiver);
                jsonObject.put("message", message);
                jsonObject.put("time", time);
                String data = jsonObject.toString();
                messageArrayList.add(data);

                setStatus(sender, receiver, time, 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return messageArrayList;
    }

    /**
     * 获取用户A和用户B之间所有的聊天记录，以json格式返回
     *
     * @param first
     * @param second
     * @return
     */
    @Override
    public ArrayList<String> getChatRecord(String first, String second) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        conn = JDBCUtil.getConnection();
        String sql = "select * from message where sender=? and receiver=? or receiver=? and sender=?" +
                " order by time";
        ArrayList<String> messageArrayList = new ArrayList<String>();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, first);
            pst.setString(2, second);
            pst.setString(3, first);
            pst.setString(4, second);
            rs = pst.executeQuery();
            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                String sender = rs.getString("sender");
                String receiver = rs.getString("receiver");
                String message = rs.getString("message");
                String time = rs.getString("time");
                jsonObject.put("sender", sender);
                jsonObject.put("receiver", receiver);
                jsonObject.put("message", message);
                jsonObject.put("time", time);
                String data = jsonObject.toString();
                messageArrayList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return messageArrayList;
    }
}

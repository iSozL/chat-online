package dao;

import utils.JDBCUtil;

import java.sql.*;

public class OnlineUserDaoImpl implements OnlineUserDao {

    /**
     * 在线列表添加用户
     *
     * @param userName
     * @return
     */
    @Override
    public boolean addUser(String userName) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;

        conn = JDBCUtil.getConnection();
        String sql = "insert into user(user_name) values(?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
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
     * 用户离线，在线列表删除该用户
     *
     * @param userName
     * @return
     */
    @Override
    public boolean deleteUser(String userName) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pst = null;

        conn = JDBCUtil.getConnection();
        String sql = "delete from user where user_name=?";//如果存在，删除该用户数据
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, userName);
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
     * 判断某用户是否在线
     *
     * @param userName
     * @return
     */
    @Override
    public boolean isOnline(String userName) {
        boolean flag = false;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "select * from user where user_name ='" + userName + "'";
        conn = JDBCUtil.getConnection();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return flag;
    }
}

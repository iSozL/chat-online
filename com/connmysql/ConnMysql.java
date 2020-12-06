package com.connmysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnMysql {
    private static Connection conn;

    public ConnMysql(){
        if(isConn()!=true) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://47.114.130.91/chat_system?useSSL=false&serverTimezone=GMT", "root", "lkw19991124.");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        disconnect();
        super.finalize();
    }

    private boolean isConn(){
        return conn!=null?true:false;
    }

    public Connection getConn(){
        return conn;
    }

    private void disconnect() {
        try {
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        ConnMysql connMysql = new ConnMysql();
        conn = connMysql.getConn();
        if (conn != null)
            System.out.println("连接成功");
    }
}

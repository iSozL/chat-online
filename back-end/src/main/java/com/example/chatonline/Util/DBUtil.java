package com.example.chatonline.Util;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component("dbUtil")
public class DBUtil {
    private final static String URL = "jdbc:mysql://localhost:3306/web-jwt?serverTimezone=GMT";//数据库连接地址
    public final static String USERNAME = "root";
    public final static String PASSWORD = "hjs18279140135";
    public final static String DRIVER = "com.mysql.cj.jdbc.Driver";    //加载的驱动程序类（这个类就在我们导入的jar包中）


    public static  Connection getCon() throws Exception
    {
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return con;
    }
    public static  void closeCon(Connection con) throws Exception
    {
        if(con!=null)
            con.close();
    }
}


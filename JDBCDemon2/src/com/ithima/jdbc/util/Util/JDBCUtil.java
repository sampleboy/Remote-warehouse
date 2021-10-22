package com.ithima.jdbc.util.Util;

import javax.print.attribute.ResolutionSyntax;
import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    static String driverClass = null;
    static String url = null;
    static String name = null;
    static String password = null;

    static {
//        创建一个属性配置对象；
        try {
            Properties properties = new Properties();
//            InputStream is=new FileInputStream("jdbc.properties");
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        导入输入流；
            properties.load(is);
//            读取属性
            String driverClass = properties.getProperty("driverClass");
            String url = properties.getProperty("url");
            String name = properties.getProperty("name");
            String password = properties.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConn() {
        Connection conn = null;

        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(Connection conn, Statement st, ResultSet rs) {
        closeRs(rs);
        closeConn(conn);
        closeSt(st);
    }

    public static void release(Connection conn, Statement st) {

        closeConn(conn);
        closeSt(st);
    }

    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs = null;
        }
    }

    private static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            st = null;
        }
    }


    private static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conn = null;
        }
    }

}

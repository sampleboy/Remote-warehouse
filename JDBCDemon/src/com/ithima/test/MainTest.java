package com.ithima.test;

import com.ithima.jdbc.util.Util.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        conn = JDBCUtil.getConn();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/student", "root", "root");
//创建statemenResultSet rst,跟数据打交道，一定需要这个对象；
            st = conn.createStatement();
//执行sql，得到result；
            String sql = "select * from t_stu";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");


                System.out.println("id=" + id + "===name=" + name + "==age=" + age);
            }


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(conn, st, rs);
        }

    }
}


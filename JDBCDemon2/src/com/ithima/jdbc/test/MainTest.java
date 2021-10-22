package com.ithima.jdbc.test;

import com.ithima.jdbc.util.Util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
//          获取连接对象；
            conn = JDBCUtil.getConn();
//          根据连接对象，得到statement;
            st = conn.createStatement();
//          执行SQL语句，返回resultset；
            String sql = "select * from t_stu";
            rs = st.executeQuery(sql);
//            遍历结果集，返回；
            while (rs.next()) {

                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("name=" + name + "===age=" + age);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(conn, st, rs);
        }


    }
}

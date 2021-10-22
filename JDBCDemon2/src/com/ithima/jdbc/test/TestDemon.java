package com.ithima.jdbc.test;

import com.ithima.jdbc.util.Util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDemon {
    @Test
    public void testQuery() {
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

    @Test
    public void testInsert() {
        Connection conn = null;
        Statement st = null;


        try {
            conn = JDBCUtil.getConn();
//          根据连接对象，得到statement;
            st = conn.createStatement();
            String sql = "select * from t_stu value (null,'aobama',59)";
            int result = st.executeUpdate(sql);
            if (result > 0) {
                System.out.println("添加成功");

            } else {
                System.out.println("添加失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(conn, st);
        }
    }
}

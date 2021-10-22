package com.ithima.dao.impl;

import com.ithima.dao.UserDao;
import com.ithima.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {
    @Override
    public void findAll() {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
//        获取连接对象；
            conn = JDBCUtil.getConn();
//          创建statement，跟数据打交道；
            st = conn.createStatement();
            String sql = "select * from t_user";
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                int password = rs.getInt("password");
                System.out.println(username + "==" + password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.release(conn, st, rs);
        }


    }
}

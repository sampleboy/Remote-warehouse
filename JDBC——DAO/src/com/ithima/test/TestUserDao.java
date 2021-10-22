package com.ithima.test;

import com.ithima.dao.UserDao;
import com.ithima.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.sql.SQLException;


public class TestUserDao {
    @Test
    public void testFindAll() {

        UserDao dao = new UserDaoImpl();
        dao.findAll();
    }
}

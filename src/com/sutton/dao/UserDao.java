package com.sutton.dao;

import com.sutton.entities.User;
import com.sutton.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    // Declare a common use JDBC template object
    private  static  JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public static User login(User loginUser) {

        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

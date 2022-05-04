package com.sutton;


import com.sutton.dao.UserDao;
import com.sutton.entities.User;


public class test {

    public static void main(String[] args) {

        new test().userDaoTest();
    }


    public void userDaoTest() {

       User loginUser = new User();
       loginUser.setUsername("Sutton");
       loginUser.setPassword("123");

        User login = UserDao.login(loginUser);
        System.out.println(login);
    }
}

package com.sutton.controllers;

import com.sutton.dao.UserDao;
import com.sutton.entities.User;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        User loginUser = new User();
//        loginUser.setUsername(username);
//        loginUser.setPassword(password);


        User loginUser = new User();
        Map<String, String[]> parameterMap = req.getParameterMap();
        try {
            BeanUtils.populate(loginUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User resultUser = UserDao.login(loginUser);
        if(resultUser != null) {
            req.setAttribute("user",resultUser);
            // requestDispatcher characteristics:
            // 1.URL doesn't change even the request is send to multiple sources
            // 2.request only can send to other servlets inside the same server
            // 3.you can attach information or an object to request object and share with other servlets
            req.getRequestDispatcher("/successLogin").forward(req,resp);
        } else {
           req.setAttribute("user",loginUser);
           req.getRequestDispatcher("/failedLogin").forward(req,resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}

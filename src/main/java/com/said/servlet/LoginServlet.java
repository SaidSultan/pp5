package com.said.servlet;

import com.said.model.User;
import com.said.service.UserService;
import com.said.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/welcome/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        UserService userService = UserServiceImpl.getInstance();

        if(userService.userIsExist(login, password)) {
            User user = userService.getUserByLoginAndPassword(login, password);
            String role = user.getRole();

            req.getSession().setAttribute("user", user);

            if (role.equals("admin")) {
                resp.sendRedirect("/admin/users");
            } else if (role.equals("user")) {
                resp.sendRedirect("/user");
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.setContentType("text/html; charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Пользователь не найден");
        }
    }
}

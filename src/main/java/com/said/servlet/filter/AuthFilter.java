package com.said.servlet.filter;

import com.said.model.User;
import com.said.service.UserService;
import com.said.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;
@WebFilter("/login")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();
        User user;
        if (nonNull(session) &&
                nonNull( user = (User) session.getAttribute("user"))) {

            String role = user.getRole();
            moveToPage(req, res, role, chain);
        } else {
            moveToPage(req, res, "Не идентифицирован", chain);
        }
    }

    private void moveToPage(HttpServletRequest req, HttpServletResponse resp, String role, FilterChain chain) throws ServletException, IOException {
        if (role.equals("admin")) {
            resp.sendRedirect("/admin/users");
        } else if (role.equals("user")) {
            resp.sendRedirect("/user");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}

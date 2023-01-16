package com.RealParking.controller;

import com.RealParking.persitence.entity.User;
import com.RealParking.persitence.service.LoginService;
import com.RealParking.persitence.service.LoginServiceImpl;
import com.RealParking.persitence.service.UserService;
import com.RealParking.persitence.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService service = new LoginServiceImpl();
        Optional<String> usernameOptional = service.getUsername(req);
        if (usernameOptional.isPresent()) {
            req.setAttribute("title", "Bienvenido");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("title", "Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginService loginService = new LoginServiceImpl();
        try {
            Optional<User> userOptional = loginService.login(username, password);
            if (userOptional.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                req.setAttribute("title", "Bienvenido");
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("title", "Login");
                getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("title", "Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}

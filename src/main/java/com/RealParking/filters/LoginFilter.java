package com.RealParking.filters;

import com.RealParking.domain.service.LoginService;
import com.RealParking.domain.service.LoginServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/index.jsp", "/registro/*", "/caja/*" , "/configuracion/*",
        "/registro/*", "/reportes/*", "/roles/*", "/users/*"} )
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) req);
        if (username.isPresent()) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + "/login.jsp");
        }
    }
}

package com.example.demo.config.handler;

import com.example.demo.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,

                                        Authentication authentication) throws IOException, ServletException {
        List<Role> list = (List) authentication.getAuthorities();
        String role = list.get(0).getRole();
        if (role.equals("ADMIN")) {

            httpServletResponse.sendRedirect("/admin");
        } else if (role.equals("USER")) {
            String name = httpServletRequest.getParameter("j_username");
            httpServletResponse.sendRedirect("/user/" + name);
        }

    }
}
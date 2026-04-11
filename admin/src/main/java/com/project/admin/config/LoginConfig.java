package com.project.admin.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //   StringBuffer requestURL = request.getRequestURL();
        HttpSession session = request.getSession();

        if(session ==null || session.getAttribute("admin") ==null) {

            response.sendRedirect("/admin/login");

            return  false;

        }

        return true;
    }
}



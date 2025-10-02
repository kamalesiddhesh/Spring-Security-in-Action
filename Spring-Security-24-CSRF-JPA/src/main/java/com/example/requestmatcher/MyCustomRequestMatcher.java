package com.example.requestmatcher;

import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

public class MyCustomRequestMatcher implements RequestMatcher{
    @Override
    public boolean matches(HttpServletRequest request) {
        return request.getMethod().equals("GET") || request.getMethod().equals("POST");
    }

}

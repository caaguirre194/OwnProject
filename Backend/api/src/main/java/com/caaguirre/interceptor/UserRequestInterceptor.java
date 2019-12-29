package com.caaguirre.interceptor;

import com.caaguirre.constant.ConstantJWT;
import com.caaguirre.security.JwtUser;
import com.caaguirre.security.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRequestInterceptor extends HandlerInterceptorAdapter {

    private JwtUser jwtUser = null;

    @Autowired
    private JwtValidator validator;

    public UserRequestInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header = request.getHeader(ConstantJWT.AUTHORIZATION_HEADER);

        if (header != null && header.startsWith(ConstantJWT.BEARER_TOKEN)) {
            String authenticationHeader = header.substring(7);
            this.jwtUser = validator.validate(authenticationHeader);
        }

        return true;
    }

    public JwtUser getJwtUser() {
        return jwtUser;
    }

}
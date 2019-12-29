package com.caaguirre.security.web;

import com.caaguirre.constant.ConstantJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    protected JwtAuthenticationTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {

        String header = httpServletRequest.getHeader(ConstantJWT.AUTHORIZATION_HEADER);

        if (header == null || !header.startsWith(ConstantJWT.BEARER_TOKEN)) {
            throw new RuntimeException("AUTHORIZATION");
        }
        String authenticationHeader = header.substring(7);
        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationHeader);

        return getAuthenticationManager().authenticate(token);
    }

}


package com.caaguirre.security.model;

import com.caaguirre.constant.ConstantJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims().setSubject(jwtUser.getEmail());
        claims.put(ConstantJWT.USER_ID, String.valueOf(jwtUser.getId()));
        claims.put(ConstantJWT.ROLE, jwtUser.getRole());
       // claims.put(ConstantJWT.NOMBRES, jwtUser.getNombres());
        //claims.put(ConstantJWT.APELLIDOS, jwtUser.getApellidos());
        return Jwts.builder().
                setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, ConstantJWT.SECRET_WORD)
                .compact();
    }

}
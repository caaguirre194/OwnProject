package com.caaguirre.security.model;

import com.caaguirre.constant.ConstantJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private static Logger LOG = LoggerFactory.getLogger(JwtValidator.class);

    public JwtUser validate(String token) {
        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(ConstantJWT.SECRET_WORD)
                    .parseClaimsJws(token).getBody();
            jwtUser = new JwtUser();
            jwtUser.setEmail(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get(ConstantJWT.USER_ID)));
            jwtUser.setRole((String) body.get(ConstantJWT.ROLE));
         //   jwtUser.setNombres((String) body.get(ConstansJWT.NOMBRES));
         //   jwtUser.setApellidos((String) body.get(ConstansJWT.APELLIDOS));
        } catch (Exception e) {
            LOG.error("Error al validar token ", e);
        }
        return jwtUser;
    }

}

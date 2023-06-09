package org.medtech.medmeet.authentication.handler;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.medtech.medmeet.authentication.domain.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {

    @Value("${app.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Date expirationDate = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000); // 7 days

        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }
}

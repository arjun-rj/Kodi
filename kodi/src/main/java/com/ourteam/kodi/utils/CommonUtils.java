package com.ourteam.kodi.utils;

import com.ourteam.kodi.common.ReturnStatus;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import com.ourteam.kodi.common.Constants;

public class CommonUtils {

    public static ReturnStatus<String> generateUserToken(String userId, String userName, String phone) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(Constants.JWT_SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String token = Jwts.builder()
                .claim("name", userName)
                .claim("phone", phone)
                .setId(userId)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.HOURS)))
                .signWith(hmacKey)
                .compact();
        return new ReturnStatus<>(true, token, null);
    }

    public static ReturnStatus<Jws<Claims>> parseJwt(String jwtString) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(Constants.JWT_SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        try {
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(jwtString);
            return new ReturnStatus<>(true, jwt, null);
        } catch (ExpiredJwtException ex) {
            return new ReturnStatus<>(false, null, ex.getMessage());
        }
    }
}

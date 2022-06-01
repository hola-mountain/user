package com.holamountain.userdomain.jwt;

import com.holamountain.userdomain.model.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.accessExpires}")
    private String accessExpiresString;

    @Value("${jwt.refreshExpires}")
    private String refreshExpiresString;

    public String createAccessJwtToken(UserEntity userEntity) {
        Date expiration = new Date(System.currentTimeMillis() + Long.parseLong(accessExpiresString));
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        return Jwts.builder()
                .claim("userId", userEntity.getUserId())
                .claim("userType", userEntity.getUserType())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiration) // 만료시간
                .compact();
    }

    public JwtTokenInfo createRefreshJwtToken(UserEntity userEntity) {
        Date expiration = new Date(System.currentTimeMillis() + Long.parseLong(refreshExpiresString));
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return JwtTokenInfo.builder()
                .userId(userEntity.getUserId())
                .refreshToken(Jwts.builder()
                                    .claim("userId", userEntity.getUserId())
                                    .claim("userType", userEntity.getUserType())
                                    .signWith(key, SignatureAlgorithm.HS256) // 해시값
                                    .setExpiration(expiration) // 만료시간
                                    .compact())
                .refreshTokenExpirationTime(expiration)
                .build();
    }

    public String getUserIdFromAccessToken(String accessToken) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().get("userId").toString();
    }

    public String getUserTypeFromAccessToken(String accessToken) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().get("userType").toString();
    }
}

package io.novelis.onlineblog.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {



    // Generate a secure key for HS384 algorithm
//    private final String secret = Keys.secretKeyFor(SignatureAlgorithm.HS384).toString();
    private final String secret = "owifofiawjfoaijwfawjefowwwosqjijsiqisiqijiieejfoiw";


    public String generateToken(String userName)
    {

        return  Jwts.builder().setSubject(userName).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUsername(String token) {

        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}


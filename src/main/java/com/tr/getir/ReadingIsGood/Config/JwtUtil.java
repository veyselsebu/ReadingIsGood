package com.tr.getir.ReadingIsGood.Config;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    private final String secret = "GETIR_STUDY_READING_IS_GOOD_SECRET";

    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+(1000*60*60*2))).
                signWith(SignatureAlgorithm.HS256,secret).compact();
    }
    public String genarateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return  createToken(claims,userName);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public <T>T extarctClaim (String token, Function<Claims,T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    public String  extractUserName(String token){
        return extarctClaim(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extarctClaim(token,Claims::getExpiration);
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String tokenUserName = extractUserName(token);
        try {
            return (tokenUserName.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e){
            return false;
        }

    }
    public String token2UserId (String token){
        String tt = token.substring(7);
        return extarctClaim(tt,Claims::getSubject);
    }

}

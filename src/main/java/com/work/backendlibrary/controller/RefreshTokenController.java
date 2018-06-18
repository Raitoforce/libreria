package com.work.backendlibrary.controller;

import com.work.backendlibrary.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@RestController
@RequestMapping
public class RefreshTokenController{
    Authentication auth;

    @PostMapping(path = "/token={tokenIn}")
    public void updateToken(HttpServletResponse response,@PathVariable("tokenIn") String tokenIn){
    	String user;
    	//Claims claims;
    	//boolean isExpired=false;
    	//Date today=new Date(System.currentTimeMillis());
        auth = SecurityContextHolder.getContext().getAuthentication();
        try{
        	if (tokenIn!=null){
        	 		user = Jwts.parser()
                     .setSigningKey(SecurityConstants.SECRET.getBytes())
                     .parseClaimsJws(tokenIn.replace(SecurityConstants.TOKEN_PREFIX, ""))
                     .getBody()
                     .getSubject();
        	 		
        	//if(user.compareTo(auth.getName())==0){
        		//claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET.getBytes()).parseClaimsJws(tokenIn.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody();
        	}	
        			//isExpired=today.after(claims.getExpiration());
        }catch (ExpiredJwtException e) {
        			response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + generarToken(auth));
		}
        	//}
      }
    


    private String generarToken(Authentication authentication){
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
        //res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        return token;
    }


}

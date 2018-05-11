package com.work.backendlibrary.controller;

import com.work.backendlibrary.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@RestController
@RequestMapping
public class RefreshTokenController{
    Authentication auth;

    @PostMapping(path = "/token")
    public void updateToken(HttpServletRequest request, HttpServletResponse response){
        String tokenIn = request.getHeader(SecurityConstants.HEADER_STRING);
        auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal()!=null || !tokenIn.isEmpty()){
            response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + generarToken(auth));
        }
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

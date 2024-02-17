package io.novelis.onlineblog.api.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilterer extends OncePerRequestFilter{

    private final JwtUtils jwtUtils;

    public JwtFilterer(){
        jwtUtils = new JwtUtils();
    }


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token =getToken(request);

        if(token!= null){
            String username = jwtUtils.extractUsername(token);
            System.out.println("username from token : " + username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(username ,null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request)
    {
        String token=request.getHeader("Authorization");

        if(StringUtils.hasText(token) && token.startsWith("Bearer "))
        {
            return  token.substring(7);
        }
        return null;
    }
}

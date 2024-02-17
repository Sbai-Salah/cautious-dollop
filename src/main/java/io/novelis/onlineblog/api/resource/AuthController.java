package io.novelis.onlineblog.api.resource;

import io.novelis.onlineblog.api.security.JwtUtils;
import io.novelis.onlineblog.service.dto.AuthResponse;
import io.novelis.onlineblog.service.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        final String jwt = jwtUtils.generateToken(userDetails.getUsername());
        System.out.println("JWT :" + jwt);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
    @GetMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("JWT from login : "  + jwtUtils.generateToken(loginRequest.getUserName()));
        return jwtUtils.generateToken(loginRequest.getUserName());
    }


}


package com.devchasers.authenticationms.controller;


import com.devchasers.authenticationms.security.SecurityConfig;
import com.devchasers.authenticationms.security.TokenProvider;
import com.devchasers.authenticationms.service.UserService;
import com.devchasers.authenticationms.util.JwtRespone;
import com.devchasers.authenticationms.util.LoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Import(SecurityConfig.class)
@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("auth/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody LoginModel loginModel){

        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginModel.getEmail(),
                            loginModel.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginModel.getEmail());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token;
            token = tokenProvider.generateToken(userDetails,1);
            return ResponseEntity.ok(new JwtRespone(token,userService.findByEmail(loginModel.getEmail())));

        } catch (AuthenticationException e) {
            // return 403  forbidden response and error message
            return ResponseEntity.status(403).body("Email or password is incorrect");

        }

    }
}

package com.miloraddjordjevic.ecommerce.controllers;

import com.miloraddjordjevic.ecommerce.security.TokenUtils;
import com.miloraddjordjevic.ecommerce.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Controller
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        System.out.println(username + " " + password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new ResponseEntity<>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
    }
}

package com.miloraddjordjevic.ecommerce.controllers;

import com.miloraddjordjevic.ecommerce.entities.User;
import com.miloraddjordjevic.ecommerce.security.TokenUtils;
import com.miloraddjordjevic.ecommerce.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login (Model model, @RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        model.addAttribute("user", user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return new ResponseEntity<>(tokenUtils.generateToken(userDetails), HttpStatus.OK);
    }

    @PreAuthorize (value = "hasRole('USER')")
    @RequestMapping (value = "/bad_login")
    public ResponseEntity<String> no() {
        return new ResponseEntity<>("Los loginnn", HttpStatus.OK);
    }

    @RequestMapping (value = "/index")
    public ResponseEntity<String> yes() {
        return new ResponseEntity<>("Dobrodosli", HttpStatus.OK);
    }
}

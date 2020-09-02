package com.miloraddjordjevic.ecommerce.controllers;

import com.miloraddjordjevic.ecommerce.entities.User;
import com.miloraddjordjevic.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Controller
public class RegisterController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping (value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser (@RequestParam (name = "email") String email, @RequestParam (name = "username") String username, @RequestParam (name = "password") String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return new ResponseEntity<>("Uspesna registracija!", HttpStatus.OK);
    }
}

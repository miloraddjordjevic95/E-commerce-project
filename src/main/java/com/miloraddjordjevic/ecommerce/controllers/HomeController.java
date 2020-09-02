package com.miloraddjordjevic.ecommerce.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@Controller
public class HomeController {

    @RequestMapping (value = "/home", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }
}

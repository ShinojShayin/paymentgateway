package com.paymentgateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/hellospring")
    public String helloSpring() {
        return "index";
    }

}

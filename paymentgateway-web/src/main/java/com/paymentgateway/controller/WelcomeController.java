package com.paymentgateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/")
    public String helloApp() {

        return "index";
    }

    @RequestMapping("/home")
    public String helloApp1() {
        System.out.print("got it");
        return "index";
    }

}

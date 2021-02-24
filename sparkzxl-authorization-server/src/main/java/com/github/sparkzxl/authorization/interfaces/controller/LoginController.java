package com.github.sparkzxl.authorization.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/authentication/require")
    public String require() {
        return "login";
    }

}

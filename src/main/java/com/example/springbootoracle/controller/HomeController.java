package com.example.springbootoracle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/index"})
    public String showMap() {
        return "index";
    }
}

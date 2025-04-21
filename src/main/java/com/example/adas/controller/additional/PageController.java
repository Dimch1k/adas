package com.example.adas.controller.additional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String home() {
        return "forward:/navbar.html";
    }

    @GetMapping("/access-denied")
    public String home2() {
        return "forward:/navbar.html";
    }
}

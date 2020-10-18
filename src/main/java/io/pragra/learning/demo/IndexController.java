package io.pragra.learning.demo;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping("/")
    public String getHome() {
        return "index";
    }
}

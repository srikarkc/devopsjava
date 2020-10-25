package io.pragra.learning.demo;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping("/")
    public String getHome() {
        String param = null;
        if(param ==null || param =="") {
            System.out.println("Somthign is not right");
        }

        return "index";
    }
}

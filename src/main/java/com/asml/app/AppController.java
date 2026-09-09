package com.asml.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String home() {
        return "ASML DevOps Application is running successfully";
    }

    @GetMapping("/api/status")
    public String status() {
        return "Application Status: UP";
    }
}

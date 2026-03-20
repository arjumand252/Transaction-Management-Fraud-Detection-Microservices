package com.fraudapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraudController {

    @GetMapping("/fraud/health")
    public String health() {
        return "Fraud service running";
    }
}

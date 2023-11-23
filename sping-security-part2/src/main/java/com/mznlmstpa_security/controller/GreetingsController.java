package com.mznlmstpa_security.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/greetings")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok().body("Hello From Our Api");
    }

    @GetMapping("/SayGoodBye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok().body("Say God Bye From Api");
    }
}

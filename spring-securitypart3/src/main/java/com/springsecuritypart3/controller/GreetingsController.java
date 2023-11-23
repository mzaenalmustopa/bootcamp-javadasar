package com.springsecuritypart3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/greetings")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
       return ResponseEntity.ok("Hello From Our Api");
    }

    @GetMapping("/saygodbye")
    public ResponseEntity<String> sayGodBye(){
        return ResponseEntity.ok("Say Good Bye See You Later");
    }
}

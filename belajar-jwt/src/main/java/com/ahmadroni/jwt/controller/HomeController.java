package com.ahmadroni.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
    @GetMapping("/hello")
    public ResponseEntity<Object> sayHello(){
        return ResponseEntity.ok().body("Hello to JWT ...");
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<Object> sayHelloName(@PathVariable("name") String name){
        return ResponseEntity.ok().body("Hello "+ name +" to JWT ...");
    }
}

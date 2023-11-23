package com.ahmadroni.javalogic.controller;

import com.ahmadroni.javalogic.model.PalindromeRequest;
import com.ahmadroni.javalogic.service.Logic01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/logic01")
public class Logic01Controller {
    private final Logic01Service logic01Service;

    @Autowired
    public Logic01Controller(Logic01Service logic01Service) {
        this.logic01Service = logic01Service;
    }

    @GetMapping("/soal01/{param}")
    public ResponseEntity<Object> soal01(@PathVariable("param") String param){
        Map<Character, Integer> result = logic01Service.soal01(param);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/soal02")
    public ResponseEntity<Object> soal02(@RequestBody PalindromeRequest request){
        PalindromeRequest result = logic01Service.soal02(request);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/soal03/{param}")
    public ResponseEntity<Object> soal03(@PathVariable("param") Integer param){
        Map<Integer, String> result = logic01Service.soal03(param);
        return ResponseEntity.ok().body(result);
    }
}

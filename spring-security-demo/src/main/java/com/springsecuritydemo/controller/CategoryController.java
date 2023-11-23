package com.springsecuritydemo.controller;

import com.springsecuritydemo.dto.CategoryDto;
import com.springsecuritydemo.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @GetMapping
    public ResponseEntity<Response> getAll(){
        List<CategoryDto> list = Arrays.asList(
                new CategoryDto(1,"CT001","Makanan"),
                new CategoryDto(2,"CT002","Minuman")
        );

        return ResponseEntity.ok().body(new Response(200, "Success", list));
    }
}

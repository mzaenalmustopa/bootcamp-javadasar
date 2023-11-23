package com.mznlmstpa_security.controller;

import com.mznlmstpa_security.dto.CartDto;
import com.mznlmstpa_security.dto.Response;
import com.mznlmstpa_security.entity.CartEntity;
import com.mznlmstpa_security.entity.ProductEntity;
import com.mznlmstpa_security.repository.ProductRepo;
import com.mznlmstpa_security.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductRepo productRepo;

    @GetMapping("")
    public ResponseEntity<Response> getAll(){
        List<CartEntity> result = this.cartService.getAll();
        return ResponseEntity.ok().body(new Response(200,"Success",result));
    }

    @GetMapping(value = "/product", produces = {"application/json"})
    public ResponseEntity<Response> getProduct(){
        List<ProductEntity> result = this.productRepo.findAll();
        return ResponseEntity.ok().body(new Response(200, "Success", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable("id")String id){
        return ResponseEntity.ok().body(new Response(200,"Success", this.cartService.getById(id)));
    }

    @PostMapping("")
    public ResponseEntity<Response> save(@RequestBody CartDto request){
        Optional<CartEntity> result = this.cartService.save(request);

        if (result.isPresent()){
            return ResponseEntity.ok().body(new Response(200,"Success",result));
        }else {
            return ResponseEntity.status(500).body(new Response(500, "Failed", null));
        }
    }

    @PostMapping("/simple")
    public ResponseEntity<Response> simpleSave(@RequestBody CartDto request){
        Optional<CartEntity> result = this.cartService.simpleSave(request);

        if (result.isPresent()){
            return ResponseEntity.ok().body(new Response(200,"Success",result));
        }else {
            return ResponseEntity.status(500).body(new Response(500, "Failed", null));
        }
    }
}

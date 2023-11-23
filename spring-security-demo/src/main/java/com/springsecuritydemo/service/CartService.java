package com.springsecuritydemo.service;

import com.springsecuritydemo.dto.CartDto;
import com.springsecuritydemo.entity.CartEntity;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<CartEntity> getAll();
    Optional<CartEntity> getById(String id);
    Optional<CartEntity> save(CartDto request);
    Optional<CartEntity> simpleSave(CartDto request);
}

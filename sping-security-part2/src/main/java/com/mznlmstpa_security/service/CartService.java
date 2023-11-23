package com.mznlmstpa_security.service;

import com.mznlmstpa_security.dto.CartDto;
import com.mznlmstpa_security.entity.CartEntity;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<CartEntity> getAll();
    Optional<CartEntity> getById(String id);
    Optional<CartEntity> save(CartDto request);
    Optional<CartEntity> simpleSave(CartDto request);
}

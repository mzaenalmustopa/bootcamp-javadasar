package com.mznlmstpa_security.repository;

import com.mznlmstpa_security.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, String> {
}

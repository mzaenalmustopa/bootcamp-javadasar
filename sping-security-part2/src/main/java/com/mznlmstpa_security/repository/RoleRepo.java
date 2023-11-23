package com.mznlmstpa_security.repository;

import com.mznlmstpa_security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByName (String name);
}

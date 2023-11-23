package com.springsecuritydemo.service;

import com.springsecuritydemo.dto.UserDto;
import com.springsecuritydemo.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<UserEntity> getAll();
    Optional<UserEntity> getById(String id);
    Optional<UserEntity> getByEmail(String email);
    Optional<UserEntity> save(UserDto request);
    Optional<UserEntity> update( String id, UserDto request);
    Optional<UserEntity> delete(String id);
}

package com.mznlmstpa_security.dto;

import com.mznlmstpa_security.entity.RoleEntity;
import com.mznlmstpa_security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String fullName;
    private String email;
    private List<String> roles;

    public UserResponse(UserEntity entity) {
        this.id = UUID.randomUUID().toString();
        this.fullName = entity.getFullName();
        this.email = entity.getEmail();
        this.roles = entity.getRoles().stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());
    }
}

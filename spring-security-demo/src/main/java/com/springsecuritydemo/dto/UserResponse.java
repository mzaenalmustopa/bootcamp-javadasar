package com.springsecuritydemo.dto;

import com.springsecuritydemo.entity.RoleEntity;
import com.springsecuritydemo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String fullName;
    private String email;
    private List<String> roles;

    public UserResponse(UserEntity entity) {
        this.id = entity.getId();
        this.fullName = entity.getFullName();
        this.email = entity.getEmail();
        this.roles = entity.getRoles().stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());
    }
}

package com.mznlmstpa_security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String email;
    private String password;
    private String roles;
}

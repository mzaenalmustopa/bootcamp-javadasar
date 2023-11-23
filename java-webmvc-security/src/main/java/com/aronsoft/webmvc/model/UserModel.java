package com.aronsoft.webmvc.model;

import com.aronsoft.webmvc.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Roni Purwanto
 * @since : 01/05/2021
 **/
@Getter
@Setter
public class UserModel {
    private String id;
    @NotBlank
    @NotEmpty
    private String username;
    @NotBlank
    @NotEmpty
    private String password;
    @NotBlank
    @NotEmpty
    private String email;
    @NotBlank
    @NotEmpty
    private String role;
    private List<String> roles = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(UserEntity data) {
        this.id = data.getId();
        this.username = data.getUsername();
        this.password = "";
        this.email = data.getEmail();
        if(data.getRoles().size() > 0) {
            this.roles = data.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
            this.role = new ArrayList<>(data.getRoles()).get(0).getName();
        }
    }
}

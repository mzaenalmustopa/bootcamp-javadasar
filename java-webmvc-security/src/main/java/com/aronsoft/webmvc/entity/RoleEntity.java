package com.aronsoft.webmvc.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_tab")
public class RoleEntity{
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name="role_name", unique = true, nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<UserEntity> users = new HashSet<>();

    public RoleEntity(String name) {
        this.name = name;
    }

    @PrePersist
    protected void onCreated() {
        id = UUID.randomUUID().toString().replace("-","");
    }
}

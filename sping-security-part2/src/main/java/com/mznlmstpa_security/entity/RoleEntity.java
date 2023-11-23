package com.mznlmstpa_security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_role")
public class RoleEntity {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", unique = true)
    private String name;
}

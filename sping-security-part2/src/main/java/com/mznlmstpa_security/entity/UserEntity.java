package com.mznlmstpa_security.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "full_name", length = 120)
    private String fullName;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "password", length = 1028)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role",joinColumns = @JoinColumn  (name = "user_id", referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
    private List<RoleEntity> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = this.roles.stream()
                .map( x -> new SimpleGrantedAuthority(x.getName()))
                .toList();
        return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.aronsoft.webmvc.config;

import com.aronsoft.webmvc.entity.RoleEntity;
import com.aronsoft.webmvc.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private UserEntity userTab;

    public UserPrincipal(UserEntity userTab) {
        this.userTab = userTab;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        this.userTab.getRoles().forEach(role ->{
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userTab.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userTab.getUsername();
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

    public String getUserId(){
        return userTab.getId();
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        userTab.getRoles().forEach(role ->{
            roles.add(role.getName());
        });
        return roles;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<RoleEntity> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<RoleEntity> roles) {
        List<String> privileges = new ArrayList<>();
        for (RoleEntity role : roles) {
            privileges.add(role.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}

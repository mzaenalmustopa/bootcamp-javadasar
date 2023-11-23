package com.mznlmstpa_security.serviceImpl;

import com.mznlmstpa_security.dto.UserDto;
import com.mznlmstpa_security.entity.RoleEntity;
import com.mznlmstpa_security.entity.UserEntity;
import com.mznlmstpa_security.repository.RoleRepo;
import com.mznlmstpa_security.repository.UserRepo;
import com.mznlmstpa_security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAll() {
        return this.userRepo.findAll();
    }

    @Override
    public Optional<UserEntity> getById(String id) {
        return this.userRepo.findById(id);
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> save(UserDto request) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);

        userEntity.setId(UUID.randomUUID().toString());

        Optional<RoleEntity> role = roleRepo.findByName(request.getRoles());
        role.ifPresent(roleEntity -> userEntity.setRoles(Arrays.asList(roleEntity)));

        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            this.userRepo.save(userEntity);
            log.info("Save user to database success");
            return Optional.of(userEntity);
        }catch (Exception e){
            log.error("save user to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> update(String id, UserDto request) {
        if (id == null || id.isEmpty()){
            return Optional.empty();
        }

        UserEntity entity = this.userRepo.findById(id).orElse(null);
        if (entity == null){
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, entity);
        Optional<RoleEntity> role = roleRepo.findByName(request.getRoles());
        role.ifPresent(roleEntity -> entity.setRoles(Arrays.asList(roleEntity)));

        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            this.userRepo.save(entity);
            log.info("update user to database success");
            return Optional.of(entity);
        }catch (Exception e){
            log.error("update user to database failed, error{}",e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> delete(String id) {
        return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}

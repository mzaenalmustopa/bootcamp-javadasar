package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.RoleEntity;
import com.aronsoft.webmvc.entity.UserEntity;
import com.aronsoft.webmvc.model.UserModel;
import com.aronsoft.webmvc.repository.UserRepo;
import com.aronsoft.webmvc.service.RoleService;
import com.aronsoft.webmvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepo repo;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepo repo, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long getCount() {
        return this.repo.count();
    }

    @Override
    public List<UserEntity> get() {
        List<UserEntity> result = this.repo.findAll();
        if(result == null)
            result = Collections.emptyList();
        log.info("Get User data: {}", result);
        return result;
    }

    @Override
    public List<UserModel> getDto() {
        List<UserEntity> result = this.repo.findAll();
        if(result.size() == 0 ) {
            log.info("Get User data empty");
            return Collections.emptyList();
        }else {
            log.info("Get User data result: {}", result);
            return result.stream().map(UserModel::new).collect(Collectors.toList());
        }
    }

    @Override
    public List<UserModel> getDtoByKeyword(String keyword) {
        List<UserEntity> result = this.repo.findByUsernameContaining(keyword);
        if(result.size() == 0 ) {
            log.info("Get User data empty");
            return Collections.emptyList();
        }else {
            log.info("Get User data result: {}", result);
            return result.stream().map(UserModel::new).collect(Collectors.toList());
        }
    }

    @Override
    public UserEntity getByUsername(String names) {
        UserEntity result = this.repo.findByUsername(names);
        if(result == null)
            result = new UserEntity();
        log.info("Get User by name: {}, data: {}", names, result);
        return result;
    }

    @Override
    public UserEntity getById(String id) {
        UserEntity result = this.repo.findByIdFetchRoles(id);
        log.info("Get User data by id: {}, data: {} ", id, result);
        return result;
    }

    @Override
    public UserEntity save(UserEntity data) {
        try{
            this.repo.save(data);
            log.info("Save User data is success, data: {}", data);
            return data;
        }catch (Exception e){
            log.error("Save User error: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntity save(UserModel data) {
        if(data.getId() == null || data.getId().equals("")) {
            UserEntity UserEntity = new UserEntity(data, passwordEncoder);
            if(data.getRoles().size() > 0){
                List<RoleEntity> RoleEntitys = this.roleService.getByNames(data.getRoles());
                if(RoleEntitys.size() > 0){
                    UserEntity.setRoles(new HashSet<>(RoleEntitys));
                }
            }

            if(!data.getRole().equals("") && data.getRole() != null){
                RoleEntity RoleEntity = this.roleService.getByName(data.getRole());
                UserEntity.setRoles(new HashSet<>(Arrays.asList(RoleEntity)));
            }
            return this.save(UserEntity);
        }else {
            return this.update(data);
        }
    }

    @Override
    public UserEntity update(UserEntity data, String id) {
        UserEntity result = this.repo.findById(id).orElse(null);
        if(result != null) {
            try{
                BeanUtils.copyProperties(data, result);
                this.repo.save(result);
                log.info("Update User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Save User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", id);
            return null;
        }
    }

    @Override
    public UserEntity update(UserEntity data) {
        UserEntity result = this.repo.findById(data.getId()).orElse(null);
        if(result != null) {
            try{
                BeanUtils.copyProperties(data, result);
                this.repo.save(result);
                log.info("Update User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Update User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", data.getId());
            return null;
        }
    }

    @Override
    public UserEntity update(UserModel data) {
        UserEntity result = this.repo.findById(data.getId()).orElse(null);
        if(result != null) {
            try{
                BeanUtils.copyProperties(data, result);
                result.setPassword(passwordEncoder.encode(data.getPassword()));
                this.repo.save(result);
                log.info("Update User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Update User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", data.getId());
            return null;
        }
    }

    @Override
    public UserEntity delete(String id) {
        UserEntity result = this.repo.findByIdFetchRoles(id);
        if(result != null) {
            try{
                if(result.getRoles().size() > 0){
                    for(RoleEntity RoleEntity: result.getRoles()){
                        result.removeRole(RoleEntity);
                    }
                }
                this.repo.delete(result);
                log.info("Delete User data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Save User error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find User with id: {}", id);
            return null;
        }
    }
}

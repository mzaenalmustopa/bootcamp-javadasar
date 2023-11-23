package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.UserEntity;
import com.aronsoft.webmvc.model.UserModel;

import java.util.List;

/**
 * @author : Roni Purwanto
 * @since : 01/05/2021
 **/
public interface UserService {
    public Long getCount();
    public List<UserEntity> get();
    public List<UserModel> getDto();
    public List<UserModel> getDtoByKeyword(String keyword);
    public UserEntity getByUsername(String name);
    public UserEntity getById(String id);
    public UserEntity save(UserEntity data);
    public UserEntity save(UserModel data);
    public UserEntity update(UserEntity data, String id);
    public UserEntity update(UserEntity data);
    public UserEntity update(UserModel data);
    public UserEntity delete(String id);
}

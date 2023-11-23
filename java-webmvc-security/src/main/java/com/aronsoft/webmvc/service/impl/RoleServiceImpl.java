package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.RoleEntity;
import com.aronsoft.webmvc.repository.RoleRepo;
import com.aronsoft.webmvc.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo repo;

    @Autowired
    public RoleServiceImpl(RoleRepo repo) {
        this.repo = repo;
    }

    @Override
    public Long getCount() {
        return this.repo.count();
    }

    @Override
    public List<RoleEntity> get() {
        List<RoleEntity> result = this.repo.findAll();
        if(result == null)
            result = Collections.emptyList();
        log.info("Role get all data, result: {}", result);
        return result;
    }

    @Override
    public List<RoleEntity> getByNames(List<String> names) {
        List<RoleEntity> result = this.repo.findByNamesFetchMenus(names);
        if(result == null)
            result = Collections.emptyList();
//        log.info("Role get data by name: {}, result: {}", names, result);
        return result;
    }

    @Override
    public RoleEntity getByName(String name) {
        RoleEntity result = this.repo.findByName(name);
//        log.info("Role get data by name: {}, result: {}", name, result);
        return result;
    }

    @Override
    public RoleEntity getById(String id) {
        RoleEntity result = this.repo.findById(id).orElse(null);
        log.info("Role get data by id: {}, result: {}", id, result);
        return result;
    }

    @Override
    public RoleEntity save(RoleEntity data) {
        try{
            this.repo.save(data);
            log.info("Save Role data is success, data: {}", data);
            return data;
        }catch (Exception e){
            log.error("Save Role error: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RoleEntity> save(List<RoleEntity> data) {
        try{
            this.repo.saveAll(data);
            log.info("Save Role data is success, data: {}", data);
            return data;
        }catch (Exception e){
            log.error("Save Role error: {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RoleEntity update(RoleEntity data, String id) {
        RoleEntity result = this.repo.findById(id).orElse(null);
        if(result != null) {
            try{
                BeanUtils.copyProperties(data, result);
                this.repo.save(result);
                log.info("Update Role data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Save Role error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find Role with id: {}", id);
            return null;
        }
    }

    @Override
    public RoleEntity delete(String id) {
        RoleEntity result = this.repo.findByIdFetchMenuAndPrivilege(id);
        if(result != null) {
            try{
                this.repo.delete(result);
                log.info("Update Role data is success, data: {}", result);
                return result;
            }catch (Exception e){
                log.error("Save Role error: {}", e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else {
            log.info("Can not find Role with id: {}", id);
            return null;
        }
    }
}

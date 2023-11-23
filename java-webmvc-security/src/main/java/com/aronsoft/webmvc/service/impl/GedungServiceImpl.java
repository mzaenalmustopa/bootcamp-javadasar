package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.GedungEntity;
import com.aronsoft.webmvc.model.GedungModel;
import com.aronsoft.webmvc.repository.GedungRepo;
import com.aronsoft.webmvc.service.GedungService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GedungServiceImpl implements GedungService {
    private GedungRepo repo;

    @Autowired
    public GedungServiceImpl(GedungRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<GedungModel> get() {
        return this.repo.findAll().stream().map(GedungModel::new).collect(Collectors.toList());
    }

    @Override
    public Boolean validCode(GedungModel model) {
        // check code
        List<GedungEntity> checkCode = this.repo.findByCode(model.getCode());
        return checkCode.isEmpty();
    }

    @Override
    public Boolean validName(GedungModel model) {
        // check name
        List<GedungEntity> checkName = this.repo.findByName(model.getName());
        return checkName.isEmpty();
    }

    @Override
    public GedungModel getById(String id) {
        if(id == null || id.isEmpty()){
            return new GedungModel();
        }
        return this.repo.findById(id).map(GedungModel::new).orElse(new GedungModel());
    }

    @Override
    public Optional<GedungModel> save(GedungModel request) {
        if(request == null) {
            return Optional.empty();
        }

        GedungEntity result = new GedungEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new GedungModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> update(String id, GedungModel request) {
        if(id == null || id.isEmpty()){
            return Optional.empty();
        }

        Optional<GedungEntity> result = this.repo.findById(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }

        GedungEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new GedungModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<GedungModel> delete(String id) {
        GedungEntity Gedung = this.repo.findById(id).orElse(null);
        if(Gedung == null) {
            return Optional.empty();
        }

        try{
            this.repo.delete(Gedung);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new GedungModel(Gedung));
    }
}

package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.GedungEntity;
import com.aronsoft.webmvc.model.GedungModel;
import com.aronsoft.webmvc.repository.GedungRepo;
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
    public GedungModel getById(String id) {
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
        GedungEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        if(!result.getRuangan().isEmpty()){
            result.getRuangan().clear();
        }
        try{
            this.repo.save(result);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new GedungModel(result));
    }
}

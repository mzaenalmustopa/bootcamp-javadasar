package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.RuangEntity;
import com.aronsoft.webmvc.model.RuangModel;
import com.aronsoft.webmvc.repository.RuangRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RuangServiceImpl implements RuangService {
    private RuangRepo repo;

    @Autowired
    public RuangServiceImpl(RuangRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<RuangModel> get() {
        return this.repo.findAll().stream().map(RuangModel::new).collect(Collectors.toList());
    }

    @Override
    public RuangModel getById(String id) {
        return this.repo.findById(id).map(RuangModel::new).orElse(new RuangModel());
    }

    @Override
    public Optional<RuangModel> save(RuangModel request) {
        if(request == null) {
            return Optional.empty();
        }

        RuangEntity result = new RuangEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new RuangModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> update(String id, RuangModel request) {
        Optional<RuangEntity> result = this.repo.findById(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }

        RuangEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new RuangModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<RuangModel> delete(String id) {
        RuangEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }
        try{
            this.repo.save(result);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new RuangModel(result));
    }
}

package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.MataKuliahEntity;
import com.aronsoft.webmvc.model.MataKuliahModel;
import com.aronsoft.webmvc.repository.MataKuliahRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MataKuliahServiceImpl implements MataKuliahService {
    private MataKuliahRepo repo;

    @Autowired
    public MataKuliahServiceImpl(MataKuliahRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<MataKuliahModel> get() {
        return this.repo.findAll().stream().map(MataKuliahModel::new).collect(Collectors.toList());
    }

    @Override
    public MataKuliahModel getById(String id) {
        return this.repo.findById(id).map(MataKuliahModel::new).orElse(new MataKuliahModel());
    }

    @Override
    public Optional<MataKuliahModel> save(MataKuliahModel request) {
        if(request == null) {
            return Optional.empty();
        }

        MataKuliahEntity result = new MataKuliahEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new MataKuliahModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> update(String id, MataKuliahModel request) {
        Optional<MataKuliahEntity> result = this.repo.findById(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }

        MataKuliahEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new MataKuliahModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<MataKuliahModel> delete(String id) {
        MataKuliahEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        try{
            this.repo.save(result);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new MataKuliahModel(result));
    }
}

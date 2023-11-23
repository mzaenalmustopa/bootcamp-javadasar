package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.FakultasEntity;
import com.aronsoft.webmvc.model.FakultasModel;
import com.aronsoft.webmvc.repository.FakultasRepo;
import com.aronsoft.webmvc.service.FakultasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FakultasServiceImpl implements FakultasService {
    private FakultasRepo repo;

    @Autowired
    public FakultasServiceImpl(FakultasRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<FakultasModel> get() {
        return this.repo.findAll().stream().map(FakultasModel::new).collect(Collectors.toList());
    }

    @Override
    public Boolean validCode(FakultasModel model) {
        // check code
        List<FakultasEntity> checkCode = this.repo.findByCode(model.getCode());
        return checkCode.isEmpty();
    }

    @Override
    public Boolean validName(FakultasModel model) {
        // check name
        List<FakultasEntity> checkName = this.repo.findByName(model.getName());
        return checkName.isEmpty();
    }

    @Override
    public FakultasModel getById(String id) {
        if(id == null || id.isEmpty()){
            return new FakultasModel();
        }
        return this.repo.findById(id).map(FakultasModel::new).orElse(new FakultasModel());
    }

    @Override
    public Optional<FakultasModel> save(FakultasModel request) {
        if(request == null) {
            return Optional.empty();
        }

        FakultasEntity result = new FakultasEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new FakultasModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> update(String id, FakultasModel request) {
        if(id == null || id.isEmpty()){
            return Optional.empty();
        }

        Optional<FakultasEntity> result = this.repo.findById(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }

        FakultasEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new FakultasModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasModel> delete(String id) {
        FakultasEntity fakultas = this.repo.findById(id).orElse(null);
        if(fakultas == null) {
            return Optional.empty();
        }

        if(!fakultas.getJurusans().isEmpty()){
            fakultas.getJurusans().clear();
            this.repo.save(fakultas);
        }

        try{
            this.repo.delete(fakultas);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new FakultasModel(fakultas));
    }
}

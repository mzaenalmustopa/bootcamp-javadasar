package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.FakultasEntity;
import com.aronsoft.webmvc.model.FakultasModel;
import com.aronsoft.webmvc.repository.FakultasRepo;
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
    public FakultasModel getById(String id) {
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
        FakultasEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        if(!result.getJurusans().isEmpty()){
            result.getJurusans().clear();
        }
        try{
            this.repo.save(result);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new FakultasModel(result));
    }
}

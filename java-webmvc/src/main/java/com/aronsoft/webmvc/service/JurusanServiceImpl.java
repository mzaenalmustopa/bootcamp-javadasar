package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.FakultasEntity;
import com.aronsoft.webmvc.entity.JurusanEntity;
import com.aronsoft.webmvc.model.JurusanModel;
import com.aronsoft.webmvc.repository.JurusanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JurusanServiceImpl implements JurusanService{
    private JurusanRepo repo;

    @Autowired
    public JurusanServiceImpl(JurusanRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<JurusanModel> get() {
        return this.repo.findAll().stream().map(JurusanModel::new).collect(Collectors.toList());
    }

    @Override
    public JurusanModel getById(String id) {
        return this.repo.findById(id).map(JurusanModel::new).orElse(new JurusanModel());
    }

    @Override
    public Optional<JurusanModel> save(JurusanModel request) {
        if(request == null) {
            return Optional.empty();
        }

        JurusanEntity result = new JurusanEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new JurusanModel(result));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> update(String id, JurusanModel request) {
        Optional<JurusanEntity> result = this.repo.findById(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }

        JurusanEntity data = result.get();
        data.setCode(request.getCode());
        data.setName(request.getName());
        FakultasEntity fakultas = new FakultasEntity(request.getFakultas().getId());
        data.setFakultas(fakultas);

        try{
            this.repo.save(data);
            return Optional.of(new JurusanModel(data));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<JurusanModel> delete(String id) {
        return Optional.empty();
    }
}

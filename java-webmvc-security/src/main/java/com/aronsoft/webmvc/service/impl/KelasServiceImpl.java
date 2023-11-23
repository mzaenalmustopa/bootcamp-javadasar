package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.KelasEntity;
import com.aronsoft.webmvc.model.KelasModel;
import com.aronsoft.webmvc.repository.KelasRepo;
import com.aronsoft.webmvc.service.KelasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KelasServiceImpl implements KelasService {
    private final KelasRepo repo;

    @Autowired
    public KelasServiceImpl(KelasRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<KelasEntity> getAll() {
        return this.repo.findAll();
    }

    @Override
    public List<KelasModel> getAllModel() {
        return this.repo.findAll().stream().map(KelasModel::new).collect(Collectors.toList());
    }

    @Override
    public Optional<KelasEntity> getById(String id) {
        if(id == null || id.isEmpty())
            return Optional.empty();

        return this.repo.findById(id);
    }

    @Override
    public Optional<KelasEntity> save(KelasModel model) {
        if(model == null)
            return Optional.empty();
        // check case 01
        List<KelasEntity> check01 = this.repo.checkCase01(model.getRuangId(), model.getNamaHari(),
                model.getJamMulai(), model.getJamSelesai());
        if(check01 != null || !check01.isEmpty()){
            return Optional.empty();
        }

        // check case 02
        //List<KelasEntity> check02 = this.repo.checkCase02();

        // check case 03

        // check case 04

        // check case 05

        // check case 06

        // save validate OK
        KelasEntity result = new KelasEntity(model);
        try{
            this.repo.save(result);
            return Optional.of(result);
        }catch (Exception e) {
            log.error("Save kelas entity failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}

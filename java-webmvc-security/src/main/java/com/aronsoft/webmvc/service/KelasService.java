package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.entity.KelasEntity;
import com.aronsoft.webmvc.model.KelasModel;

import java.util.List;
import java.util.Optional;

public interface KelasService {
    List<KelasEntity> getAll();
    List<KelasModel> getAllModel();
    Optional<KelasEntity> getById(String id);
    Optional<KelasEntity> save(KelasModel model);
}

package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.model.MataKuliahModel;

import java.util.List;
import java.util.Optional;

public interface MataKuliahService {
    public List<MataKuliahModel> get();
    public MataKuliahModel getById(String id);
    public Optional<MataKuliahModel> save(MataKuliahModel request);
    public Optional<MataKuliahModel> update(String id, MataKuliahModel request);
    public Optional<MataKuliahModel> delete(String id);
}

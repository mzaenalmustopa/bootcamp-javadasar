package com.aronsoft.webmvc.service;

import com.aronsoft.webmvc.model.MahasiswaModel;

import java.util.List;

public interface MahasiswaService {
    public List<MahasiswaModel> get();
    public MahasiswaModel getById(String id);
    public MahasiswaModel save(MahasiswaModel data);
    public MahasiswaModel update(String id, MahasiswaModel data);
    public MahasiswaModel delete(String id);
}

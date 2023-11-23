package com.aronsoft.database.service;

import com.aronsoft.database.model.MahasiswaModel;

import java.util.List;

public interface MahasiswaService {
    public List<MahasiswaModel> getAll();
    public MahasiswaModel getById(String id);
    public MahasiswaModel save(MahasiswaModel data);
    public MahasiswaModel update(String id, MahasiswaModel data);
    public MahasiswaModel delete(String id);
}

package com.bootcamp.oop.service;

import com.bootcamp.oop.model.Mahasiswa;

import java.util.List;

public interface MahasiswaService {
    public List<Mahasiswa> get();
    public Mahasiswa getById();
}

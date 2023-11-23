package com.bootcamp.oop.service;

import com.bootcamp.oop.model.Kelas;

import java.util.List;

public interface KelasService {
    public List<Kelas> get();
    public Kelas getById();
}

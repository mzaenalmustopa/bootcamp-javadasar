package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.MahasiswaEntity;
import com.aronsoft.webmvc.model.MahasiswaModel;
import com.aronsoft.webmvc.repository.MahasiswaRepo;
import com.aronsoft.webmvc.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {
    private MahasiswaRepo repository;

    @Autowired
    public MahasiswaServiceImpl(MahasiswaRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<MahasiswaModel> get() {
        List<MahasiswaEntity> result = this.repository.findAll();
        if(result.isEmpty()){
            Collections.emptyList();
        }
        // conver dari List<SiswaEntity> => List<SiswaModel>
        return result.stream().map(MahasiswaModel::new).collect(Collectors.toList());
    }

    @Override
    public MahasiswaModel getById(String id) {
        // check id
        if(id == null || id.isBlank() || id.isEmpty()) {
            return new MahasiswaModel();
        }
        Optional<MahasiswaEntity> result = repository.findById(id);
        // convert dari SiswaEntity => SiswaModel
        return result.map(MahasiswaModel::new).orElseGet(MahasiswaModel::new);
    }

    @Override
    public MahasiswaModel save(MahasiswaModel data) {
        if(data == null) {
            return new MahasiswaModel();
        }
        MahasiswaEntity result= new MahasiswaEntity(data);
        try{
            // proses simpan data => table siswa
            this.repository.save(result);
            return new MahasiswaModel(result);
        }catch (Exception e){
            return new MahasiswaModel();
        }
    }

    @Override
    public MahasiswaModel update(String id, MahasiswaModel data) {
        // check id
        if(id == null || id.isBlank() || id.isEmpty()) {
            return new MahasiswaModel();
        }

        // ambil data dari table
        Optional<MahasiswaEntity> result = repository.findById(id);
        // check data dari result
        if(result.isPresent()){
            MahasiswaEntity siswaData = result.get();
            // replace data lama dengan dataBaru
            siswaData.setName(data.getName());
            siswaData.setAlamat(data.getAlamat());
            siswaData.setJk(data.getJk());
            // update waktu
            siswaData.setUpdatedAt(LocalDateTime.now());
            siswaData.setUpdatedBy("SYSTEM");

            try{
                this.repository.save(siswaData);
                // jika berhasil
                return new MahasiswaModel(siswaData);
            }catch (Exception e){
                System.out.println("Error update: "+ e.getMessage());
            }
        }
        return new MahasiswaModel();
    }

    @Override
    public MahasiswaModel delete(String id) {
        // check id
        if(id == null || id.isBlank() || id.isEmpty()) {
            return new MahasiswaModel();
        }

        // ambil data dari table
        Optional<MahasiswaEntity> result = repository.findById(id);
        // check data dari result
        if(result.isPresent()){
            try{
                MahasiswaEntity siswaData = result.get();
                this.repository.delete(siswaData);
                // jika delete berhasil
                return new MahasiswaModel(siswaData);
            }catch (Exception e){
                System.out.println("Error delete: "+ e.getMessage());
            }
        }
        return new MahasiswaModel();
    }
}

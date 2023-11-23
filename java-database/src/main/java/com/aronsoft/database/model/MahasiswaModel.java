package com.aronsoft.database.model;

import com.aronsoft.database.entity.MahasiswaEntity;

public class MahasiswaModel {
    private String id;
    private String name;
    private String jk;
    private String alamat;

    public MahasiswaModel() {
    }

    public MahasiswaModel(MahasiswaEntity data) {
        this.id = data.getId();
        this.name = data.getName();
        this.jk = data.getJk();
        this.alamat = data.getAlamat();
    }

    public MahasiswaModel(String id, String name, String jk, String alamat) {
        this.id = id;
        this.name = name;
        this.jk = jk;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

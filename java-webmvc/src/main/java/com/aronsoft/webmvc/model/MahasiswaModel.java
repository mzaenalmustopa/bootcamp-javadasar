package com.aronsoft.webmvc.model;

import com.aronsoft.webmvc.entity.MahasiswaEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class MahasiswaModel {
    private String id;
    private String name;
    private String jk;
    private String alamat;
    private String tmpLahir;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tglLahir;
    private String agama;
    private String jurusanId;
    private JurusanModel jurusan;


    public MahasiswaModel() {
    }

    public MahasiswaModel(MahasiswaEntity data) {
        BeanUtils.copyProperties(data, this);
        if(data.getJurusan() != null){
            jurusanId = data.getJurusanId();
            jurusan = new JurusanModel(data.getJurusan());
        }
    }

    public MahasiswaModel(String id, String name, String jk, String alamat) {
        this.id = id;
        this.name = name;
        this.jk = jk;
        this.alamat = alamat;
    }
}

package com.aronsoft.database.entity;

import com.aronsoft.database.model.MahasiswaModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "siswa_tab")
public class MahasiswaEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nama_siswa", length = 120)
    private String name;

    @Column(name = "jk", length = 10)
    private String jk;

    @Column(name = "alamat", length = 200)
    private String alamat;

    @Column(name = "tmp_lahir", length = 100)
    private String tmpLahir;

    @Column(name = "tgl_lahir")
    private LocalDate tglLahir;

    @Column(name = "agama", length = 20)
    private String agama;

    @Column(name = "jurusan_id", length = 36)
    private String jurusanId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    public MahasiswaEntity() {

    }

    public MahasiswaEntity(MahasiswaModel data) {
        this.id = UUID.randomUUID().toString();
        this.name = data.getName();
        this.jk = data.getJk();
        this.alamat = data.getAlamat();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }
}

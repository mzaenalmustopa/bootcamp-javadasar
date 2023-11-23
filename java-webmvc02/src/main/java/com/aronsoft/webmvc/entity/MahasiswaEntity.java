package com.aronsoft.webmvc.entity;

import com.aronsoft.webmvc.model.MahasiswaModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "mahasiswa_tab")
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

    @Column(name = "jurusan_id", length = 36, insertable = false, updatable = false)
    private String jurusanId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jurusan_id", nullable = false)
    private JurusanEntity jurusan;

    public MahasiswaEntity() {

    }

    public MahasiswaEntity(MahasiswaModel data) {
        BeanUtils.copyProperties(data, this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
        if(data.getJurusan() != null){
            this.jurusan = new JurusanEntity(data.getJurusan().getId());
        }
    }
}

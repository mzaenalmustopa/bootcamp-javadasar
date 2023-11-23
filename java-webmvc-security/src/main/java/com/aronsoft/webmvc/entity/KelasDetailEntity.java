package com.aronsoft.webmvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "kelas_detail_tab")
public class KelasDetailEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "kelas_id", length = 36, nullable = false)
    private String kelasId;

    @ManyToOne
    @JoinColumn(name = "kelas_id", insertable = false, updatable = false)
    private KelasEntity kelas;

    @Column(name = "mahasiswa_id", length = 36, nullable = false)
    private String mahasiswaId;

    @ManyToOne
    @JoinColumn(name = "mahasiswa_id", insertable = false, updatable = false)
    private MahasiswaEntity mahasiswa;

    @Column(name = "status", length = 20)
    private String status;
}

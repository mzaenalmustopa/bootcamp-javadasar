package com.aronsoft.webmvc.entity;

import com.aronsoft.webmvc.model.KelasModel;
import com.aronsoft.webmvc.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "kelas_tab")
@NoArgsConstructor
@AllArgsConstructor
public class KelasEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "kode", length = 10)
    private String kode;

    @Column(name = "hari", length = 10)
    private String namaHari;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_mulai")
    private Date jamMulai;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_selesai")
    private Date jamSelesai;

    @Column(name = "ruang_id", length = 36, nullable = false)
    private String ruangId;

    @ManyToOne
    @JoinColumn(name = "ruang_id", insertable = false, updatable = false)
    private RuangEntity ruang;

    @Column(name = "matakuliah_id", length = 36, nullable = false)
    private String matakuliahId;

    @ManyToOne
    @JoinColumn(name = "matakuliah_id", insertable = false, updatable = false)
    private MataKuliahEntity mataKuliah;

    @Column(name = "dosen_id", length = 36)
    private String dosenId;

    @ManyToOne
    @JoinColumn(name = "dosen_id", insertable = false, updatable = false)
    private DosenEntity dosen;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "tahun_ajaran")
    private Integer tahunAjaran;

    @Column(name = "semester", length = 10)
    private String semester;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "bisa_online")
    private Boolean bisaOnline;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    public KelasEntity(KelasModel model) {
        BeanUtils.copyProperties(model, this);
        /*
        this.id = UUID.randomUUID().toString();
        this.kode = model.getKode();
        this.namaHari = model.getNamaHari();
        this.jamMulai = model.getJamMulai();
        this.jamSelesai = model.getJamSelesai();
         */
        this.createdAt=LocalDateTime.now();
        this.createdBy="SYSTEM";
    }

    public KelasEntity(String kode, String namaHari, String jamMulai, String jamSelesai, String ruangId,
                       String matakuliahId, String dosenId) {
        this.id = UUID.randomUUID().toString();
        this.kode = kode;
        this.namaHari = namaHari;
        this.jamMulai = DateUtil.getTime(jamMulai);
        this.jamSelesai = DateUtil.getTime(jamSelesai);
        this.ruangId = ruangId;
        this.matakuliahId = matakuliahId;
        this.dosenId = dosenId;
    }
}

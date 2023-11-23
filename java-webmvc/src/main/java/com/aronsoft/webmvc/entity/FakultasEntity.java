package com.aronsoft.webmvc.entity;

import com.aronsoft.webmvc.model.FakultasModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "fakultas_tab")
public class FakultasEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_fakultas", length = 20, unique = true)
    private String code;

    @Column(name = "nama_fakultas", length = 225)
    private String name;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @OneToMany(mappedBy = "fakultas", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JurusanEntity> jurusans = new HashSet<>();

    public FakultasEntity() {
    }

    public FakultasEntity(String id) {
        this.id = id;
    }

    public FakultasEntity(String code, String name, String alamat) {
        this.code = code;
        this.name = name;
        this.alamat = alamat;
        this.createdAt=LocalDateTime.now();
        this.createdBy="SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

    public FakultasEntity(FakultasModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt=LocalDateTime.now();
        this.createdBy="SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

    public void addJurusan(JurusanEntity jurusan){
        this.jurusans.add(jurusan);
        jurusan.setFakultas(this);
    }

    public void removeJurusan(JurusanEntity jurusan){
        this.jurusans.remove(jurusan);
        jurusan.setFakultas(null);
    }
}

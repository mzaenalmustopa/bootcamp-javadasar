package com.aronsoft.webmvc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mata_kuliah_tab")
public class MataKuliahEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_matkul", length = 20, unique = true)
    private String code;

    @Column(name = "nama_matkul", length = 225)
    private String name;

    @Column(name = "sks")
    private Integer sks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;
}

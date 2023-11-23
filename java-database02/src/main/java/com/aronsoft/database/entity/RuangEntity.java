package com.aronsoft.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ruang_tab")
public class RuangEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "kode_ruang", length = 20, unique = true)
    private String code;

    @Column(name = "nama_ruang", length = 225)
    private String name;

    @Column(name = "gedung_id", length = 36, insertable = false, updatable = false)
    private String gedungId;

    @Column(name = "lantai_ke")
    private Integer lantaiKe;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "gedung_id", nullable = false)
    private GedungEntity gedung;
}

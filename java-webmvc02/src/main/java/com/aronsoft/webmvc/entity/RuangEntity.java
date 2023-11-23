package com.aronsoft.webmvc.entity;

import com.aronsoft.webmvc.model.RuangModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ruang_tab")
@NoArgsConstructor
@AllArgsConstructor
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

    public RuangEntity(RuangModel model) {
        BeanUtils.copyProperties(model,this);
        // set gedung
        this.gedung = new GedungEntity(model.getGedungId());

        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "SYSTEM";
    }
}

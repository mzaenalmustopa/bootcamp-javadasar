package com.aronsoft.webmvc.model;


import com.aronsoft.webmvc.entity.MataKuliahEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MataKuliahModel {
    private String id;
    private String code;
    private String name;
    private Integer sks;

    public MataKuliahModel(MataKuliahEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}

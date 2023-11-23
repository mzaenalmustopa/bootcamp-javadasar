package com.aronsoft.webmvc.model;


import com.aronsoft.webmvc.entity.GedungEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GedungModel {
    private String id;
    private String code;
    private String name;
    private Integer jmlLantai;

    public GedungModel(GedungEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}

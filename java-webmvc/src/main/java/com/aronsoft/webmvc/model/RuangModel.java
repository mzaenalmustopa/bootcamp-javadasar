package com.aronsoft.webmvc.model;

import com.aronsoft.webmvc.entity.RuangEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuangModel {
    private String id;
    private String code;
    private String name;
    private String gedungId;
    private Integer lantaiKe;

    public RuangModel(RuangEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}

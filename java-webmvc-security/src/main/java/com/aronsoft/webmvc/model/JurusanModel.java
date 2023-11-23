package com.aronsoft.webmvc.model;

import com.aronsoft.webmvc.entity.JurusanEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class JurusanModel {
    private String id;

    @NotBlank
    @NotEmpty
    private String code;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String fakultasId;
    private String fakultasName;

    public JurusanModel() {
    }

    public JurusanModel(String code, String name, String fakultasId) {
        this.code = code;
        this.name = name;
        this.fakultasId = fakultasId;
    }

    public JurusanModel(JurusanEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if(entity.getFakultas() != null) {
            fakultasId = entity.getFakultas().getId();
            fakultasName = entity.getFakultas().getName();
        }
    }
}

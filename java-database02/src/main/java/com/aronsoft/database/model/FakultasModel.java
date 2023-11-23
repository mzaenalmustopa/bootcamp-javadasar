package com.aronsoft.database.model;

import com.aronsoft.database.entity.FakultasEntity;
import com.aronsoft.database.entity.JurusanEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class FakultasModel {
    private String id;
    private String code;
    private String name;
    private String alamat;
    private List<JurusanModel> jurusans;

    public FakultasModel() {
    }

    public FakultasModel(FakultasEntity entity) {
        BeanUtils.copyProperties(entity, this);
        /*
        if(!entity.getJurusans().isEmpty()){
            List<JurusanModel> jurusanList = new ArrayList<>();
            for(JurusanEntity jurusan: entity.getJurusans()){
                jurusanList.add(new JurusanModel(jurusan));
            }
            this.jurusans = jurusanList;
        }

         */
    }
}

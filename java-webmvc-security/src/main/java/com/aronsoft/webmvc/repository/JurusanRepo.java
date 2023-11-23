package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.JurusanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JurusanRepo extends JpaRepository<JurusanEntity, String> {
    List<JurusanEntity> findByCode(String code);
    List<JurusanEntity> findByName(String name);
}

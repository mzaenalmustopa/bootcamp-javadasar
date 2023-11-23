package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity, String> {
    List<GedungEntity> findByCode(String code);
    List<GedungEntity> findByName(String name);
    List<GedungEntity> findByCodeAndName(String code, String name);
}

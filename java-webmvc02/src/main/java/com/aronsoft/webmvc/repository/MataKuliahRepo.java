package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.MataKuliahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MataKuliahRepo extends JpaRepository<MataKuliahEntity, String> {
}

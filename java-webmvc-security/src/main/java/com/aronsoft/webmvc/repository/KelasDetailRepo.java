package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.KelasDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasDetailRepo extends JpaRepository<KelasDetailEntity, String> {

}

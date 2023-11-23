package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.GedungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GedungRepo extends JpaRepository<GedungEntity,String> {
}

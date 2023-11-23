package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.RuangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuangRepo extends JpaRepository<RuangEntity, String> {
}

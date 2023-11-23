package com.aronsoft.webmvc.repository;

import com.aronsoft.webmvc.entity.LookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LookupRepo extends JpaRepository<LookupEntity, String> {
    List<LookupEntity> findByGroups(String groups);
    Optional<LookupEntity> findByCode(String code);
}

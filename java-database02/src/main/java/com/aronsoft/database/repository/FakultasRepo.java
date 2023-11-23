package com.aronsoft.database.repository;

import com.aronsoft.database.entity.FakultasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakultasRepo extends JpaRepository<FakultasEntity, String> {
}

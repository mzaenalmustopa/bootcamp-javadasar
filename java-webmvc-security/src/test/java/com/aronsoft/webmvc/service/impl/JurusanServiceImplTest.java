package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.JurusanEntity;
import com.aronsoft.webmvc.model.JurusanModel;
import com.aronsoft.webmvc.repository.JurusanRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JurusanServiceImplTest {
    @Autowired
    @InjectMocks
    private JurusanServiceImpl service;

    @Mock
    private JurusanRepo repo;

    private static List<JurusanEntity> jurusanList;
    private static String fakultasId;

    @BeforeAll
    static void beforeAll() {
        fakultasId = UUID.randomUUID().toString();
        jurusanList = Arrays.asList(
                new JurusanEntity("TI", "Teknik Informatika", fakultasId),
                new JurusanEntity("SI", "Sistem Informatika", fakultasId),
                new JurusanEntity("MI", "Managemen Informatika", fakultasId)
        );
    }

    @Test
    void get() {
    }

    @Test
    void getById() {
    }

    @Test
    void save_Check_Code() {
        // check pertama
        Optional<JurusanModel> result = service.save(null);
        assertNotNull(result);
        assertEquals(Optional.empty(), result);

        when(repo.findByCode("TI")).thenReturn(Arrays.asList(jurusanList.get(0)));
        JurusanModel request = new JurusanModel("TI", "Teknik Informatika", fakultasId);
        // check code
        result = service.save(request);
        assertNotNull(result);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void save_check_Name() {
    }

    @Test
    void save_check_Valid() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
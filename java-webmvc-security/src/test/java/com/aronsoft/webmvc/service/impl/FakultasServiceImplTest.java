package com.aronsoft.webmvc.service.impl;

import com.aronsoft.webmvc.entity.FakultasEntity;
import com.aronsoft.webmvc.model.FakultasModel;
import com.aronsoft.webmvc.repository.FakultasRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FakultasServiceImplTest {
    @Autowired
    @InjectMocks
    private FakultasServiceImpl service;

    @Mock
    private FakultasRepo repo;

    private static List<FakultasEntity> fakultasEntityList;

    @BeforeEach
    void setUp() {
        fakultasEntityList = Arrays.asList(
                new FakultasEntity("FK", "Fakultas Kedoktorean", "Yogya"),
                new FakultasEntity("FMIPA", "Fakultas MIPA", "Yogya"),
                new FakultasEntity("FE", "Fakultas Ekonomi", "Yogya")
        );
    }

    @Test
    void get() {
        //jika method repo.findAll di panggi maka kembalikan fakultas lis
        when(repo.findAll()).thenReturn(fakultasEntityList);

        //test method get
        List<FakultasModel> result = service.get();
        // check 1
        assertNotNull(result);
        // check 2
        assertEquals(3, result.size());
        // check data 0
        assertEquals("FK", result.get(0).getCode());
        assertEquals("Fakultas Kedoktorean", result.get(0).getName());

        // check data salah
        assertNotEquals("FE", result.get(1).getCode());
    }

    @Test
    void getById() {
        // secenario 1
        FakultasModel result = service.getById("");

        assertNotNull(result);
        assertNull(result.getCode());

        // secenario kedua
        Optional<FakultasEntity> entity = Optional.of(fakultasEntityList.get(0));
        when(repo.findById(any(String.class))).thenReturn(entity);

        result = service.getById("12345");
        assertNotNull(result);
        assertEquals("FK", result.getCode());
    }

    @Test
    void save_Check_Code() {
        FakultasModel request = new FakultasModel("FE","Fakultas Ekonomi","Yogya");
        //check code
        when(repo.findByCode(request.getCode())).thenReturn(Arrays.asList(fakultasEntityList.get(2)));

        Optional<FakultasModel> result = service.save(request);
        assertNotNull(result);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void save_Check_Name() {
        FakultasModel request = new FakultasModel("FE","Fakultas Ekonomi","Yogya");
        // case kedua
        when(repo.findByCode("FE")).thenReturn(Collections.emptyList());
        when(repo.findByName("Fakultas Ekonomi")).thenReturn(Arrays.asList(fakultasEntityList.get(2)));

        Optional<FakultasModel> result = service.save(request);
        assertNotNull(result);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void save_Check_CodeAndName_Valid() {
        FakultasModel request = new FakultasModel("FE","Fakultas Ekonomi","Yogya");

        when(repo.findByCode("FE")).thenReturn(Collections.emptyList());
        when(repo.findByName("Fakultas Ekonomi")).thenReturn(Collections.emptyList());
        //stubing atau mocking
        when(repo.save(any(FakultasEntity.class))).thenReturn(fakultasEntityList.get(2));

        Optional<FakultasModel> result = service.save(request);
        assertNotNull(result);
        assertEquals("FE",result.get().getCode());
        assertEquals("Fakultas Ekonomi",result.get().getName());
        assertEquals("Yogya",result.get().getAlamat());
    }

    @Test
    void update() {
        FakultasModel result = new FakultasModel();
        assertNotNull(result);
    }

    @Test
    void delete() {
        FakultasModel result = new FakultasModel();
        assertNotNull(result);
    }
}
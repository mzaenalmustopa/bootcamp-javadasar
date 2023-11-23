package com.bootcamp.oop.controller;

import com.bootcamp.oop.model.*;
import com.bootcamp.oop.service.KelasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KelasController {
    //property
    private KelasService kelasService;

    @Autowired
    public KelasController(KelasService kelasService) {
        this.kelasService = kelasService;
    }

    @GetMapping("/kelas")
    private ResponseEntity<Object> get(){
        List<Kelas> result = kelasService.get();

        return ResponseEntity.ok()
                .body(result);
    }
}

package com.aronsoft.database.controller;

import com.aronsoft.database.model.FakultasModel;
import com.aronsoft.database.service.FakultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fakultas")
public class FakultasController {
    private FakultasService service;

    @Autowired
    public FakultasController(FakultasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<FakultasModel> result = service.get();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id){
        FakultasModel result = service.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody FakultasModel request){
        Optional<FakultasModel> result = service.save(request);
        return ResponseEntity.ok().body(result.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody FakultasModel request){
        Optional<FakultasModel> result = service.update(id, request);
        return ResponseEntity.ok().body(result.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        Optional<FakultasModel> result = service.delete(id);
        return ResponseEntity.ok().body(result.get());
    }
}

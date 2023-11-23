package com.aronsoft.database.controller;

import com.aronsoft.database.model.JurusanModel;
import com.aronsoft.database.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jurusan")
public class JurusanController {
    private JurusanService service;

    @Autowired
    public JurusanController(JurusanService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<JurusanModel> result = service.get();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id){
        JurusanModel result = service.getById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody JurusanModel request){
        Optional<JurusanModel> result = service.save(request);
        return ResponseEntity.ok().body(result.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody JurusanModel request){
        Optional<JurusanModel> result = service.update(id, request);
        return ResponseEntity.ok().body(result.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        Optional<JurusanModel> result = service.delete(id);
        return ResponseEntity.ok().body(result.get());
    }
}

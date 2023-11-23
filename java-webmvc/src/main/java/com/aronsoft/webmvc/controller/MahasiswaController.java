package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.MahasiswaModel;
import com.aronsoft.webmvc.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    private MahasiswaService service;

    @Autowired
    public MahasiswaController(MahasiswaService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("mahasiswa/index.html");
        List<MahasiswaModel> result = service.get();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("mahasiswa/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MahasiswaModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MahasiswaModel Mahasiswa = this.service.getById(id);
        if(Mahasiswa == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        ModelAndView view = new ModelAndView("mahasiswa/edit.html");
        view.addObject("data", Mahasiswa);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MahasiswaModel Mahasiswa = this.service.getById(id);
        if(Mahasiswa == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        ModelAndView view = new ModelAndView("mahasiswa/detail.html");
        view.addObject("data", Mahasiswa);
        return view;
    }
}

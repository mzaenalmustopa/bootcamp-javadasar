package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.JurusanModel;
import com.aronsoft.webmvc.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/jurusan")
public class JurusanController {
    private JurusanService service;

    @Autowired
    public JurusanController(JurusanService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("jurusan/index");
        List<JurusanModel> result = service.get();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("jurusan/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute JurusanModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        JurusanModel Jurusan = this.service.getById(id);
        if(Jurusan == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("jurusan/edit.html");
        view.addObject("data", Jurusan);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute JurusanModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        JurusanModel Jurusan = this.service.getById(id);
        if(Jurusan == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("jurusan/detail.html");
        view.addObject("data", Jurusan);
        return view;
    }
}

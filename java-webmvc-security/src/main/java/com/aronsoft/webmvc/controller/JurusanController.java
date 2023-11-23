package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.JurusanModel;
import com.aronsoft.webmvc.service.FakultasService;
import com.aronsoft.webmvc.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/jurusan")
public class JurusanController {
    private final JurusanService service;
    private final FakultasService fakultasService;

    @Autowired
    public JurusanController(JurusanService service, FakultasService fakultasService) {
        this.service = service;
        this.fakultasService = fakultasService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/jurusan/index.html");
        List<JurusanModel> result = service.get();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/jurusan/form.html");
        view.addObject("fakultasList", fakultasService.get());
        view.addObject("jurusan", new JurusanModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("jurusan") JurusanModel request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/jurusan/form.html");

        if(Boolean.FALSE.equals(service.validCode(request))){
            FieldError fieldError = new FieldError("jurusan","code","invalid code");
            result.addError(fieldError);
        }

        if(Boolean.FALSE.equals(service.validName(request))){
            ObjectError error = new ObjectError("invalidName", "Name "+ request.getName() +" Not valid");
            result.addError(error);
        }

        if(result.hasErrors()){
            view.addObject("jurusan", request);
            return view;
        }

        this.service.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        JurusanModel item = this.service.getById(id);
        if(item == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("pages/jurusan/edit.html");
        view.addObject("jurusan", item);
        view.addObject("fakultasList", fakultasService.get());
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("jurusan") JurusanModel request, BindingResult result){
        if(result.hasErrors()){
            ModelAndView view = new ModelAndView("pages/jurusan/edit.html");
            view.addObject("jurusan", request);
            return view;
        }

        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        JurusanModel item = this.service.getById(id);
        if(item == null){
            return new ModelAndView("redirect:/jurusan");
        }

        ModelAndView view = new ModelAndView("pages/jurusan/detail.html");
        view.addObject("data", item);
        view.addObject("fakultasList", fakultasService.get());
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/jurusan");
    }
}

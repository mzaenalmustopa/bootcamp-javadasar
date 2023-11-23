package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.FakultasModel;
import com.aronsoft.webmvc.service.FakultasService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fakultas")
public class FakultasController {
    private FakultasService service;

    @Autowired
    public FakultasController(FakultasService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/fakultas/index.html");
        List<FakultasModel> result = service.get();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/fakultas/form.html");
        view.addObject("fakultas", new FakultasModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("fakultas") FakultasModel request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/fakultas/form.html");
        if(Boolean.FALSE.equals(service.validCode(request))){
            FieldError fieldError = new FieldError("fakultas","code","Code "+ request.getCode() +" already exist");
            result.addError(fieldError);
        }

        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("fakultas","code","Name with "+ request.getName() +" already exist");
            result.addError(fieldError);
        }

        if(result.hasErrors()){
            view.addObject("fakultas", request);
            return view;
        }

        this.service.save(request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        FakultasModel item = this.service.getById(id);
        if(item == null){
            return new ModelAndView("redirect:/fakultas");
        }

        ModelAndView view = new ModelAndView("pages/fakultas/edit.html");
        view.addObject("fakultas", item);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("fakultas") FakultasModel request, BindingResult result){
        if(result.hasErrors()){
            ModelAndView view = new ModelAndView("pages/fakultas/edit.html");
            view.addObject("fakultas", request);
            return view;
        }

        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        FakultasModel item = this.service.getById(id);
        if(item == null){
            return new ModelAndView("redirect:/fakultas");
        }

        ModelAndView view = new ModelAndView("pages/fakultas/detail.html");
        view.addObject("data", item);
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/fakultas");
    }
}

package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.entity.LookupEntity;
import com.aronsoft.webmvc.model.MahasiswaModel;
import com.aronsoft.webmvc.service.JurusanService;
import com.aronsoft.webmvc.service.LookupService;
import com.aronsoft.webmvc.service.MahasiswaService;
import com.aronsoft.webmvc.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    private final MahasiswaService service;
    private final JurusanService jurusanService;
    private LookupService lookupService;

    @Autowired
    public MahasiswaController(MahasiswaService service, JurusanService jurusanService, LookupService lookupService) {
        this.service = service;
        this.jurusanService = jurusanService;
        this.lookupService = lookupService;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("pages/mahasiswa/index.html");
        List<MahasiswaModel> result = service.get();

        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/mahasiswa/form.html");
        view.addObject("genderList", lookupService.getByGroup(Constants.GENDER));
        view.addObject("agamaList", lookupService.getByGroup(Constants.AGAMA));
        view.addObject("jurusanList", jurusanService.get());
        // untuk order
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));

        view.addObject("mahasiswa", new MahasiswaModel());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("mahasiswa") MahasiswaModel request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/mahasiswa/form.html");
        if(result.hasErrors()){
            view.addObject("mahasiswa", request);
        }

        this.service.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MahasiswaModel data = this.service.getById(id);
        if(data == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        ModelAndView view = new ModelAndView("pages/mahasiswa/edit.html");
        view.addObject("genderList", lookupService.getByGroup(Constants.GENDER));
        view.addObject("agamaList", lookupService.getByGroup(Constants.AGAMA));
        view.addObject("jurusanList", jurusanService.get());
        // untuk order
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));
        // data yang akan diedit
        view.addObject("mahasiswa", data);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MahasiswaModel data = this.service.getById(id);
        if(data == null){
            return new ModelAndView("redirect:/mahasiswa");
        }

        ModelAndView view = new ModelAndView("pages/mahasiswa/detail.html");
        view.addObject("genderList", lookupService.getByGroup(Constants.GENDER));
        view.addObject("agamaList", lookupService.getByGroup(Constants.AGAMA));
        view.addObject("jurusanList", jurusanService.get());
        // untuk order
        view.addObject("byPosition", Comparator.comparing(LookupEntity::getPosition));
        // data yang akan diedit
        view.addObject("mahasiswa", data);
        return view;
    }
}

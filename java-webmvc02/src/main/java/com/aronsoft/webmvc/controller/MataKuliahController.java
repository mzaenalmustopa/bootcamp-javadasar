package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.MataKuliahModel;
import com.aronsoft.webmvc.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/matakuliah")
public class MataKuliahController {
    private MataKuliahService service;

    @Autowired
    public MataKuliahController(MataKuliahService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("matakuliah/index.html");
        List<MataKuliahModel> result = service.get();

        String[] array = new String[]{"B$u$i$ld", "$t$$h$e", "N$e$x$t", "E$$ra", "$$o$f$", "S$$of$t$wa$r$e", "De$$ve$l$op$me$n$t"};
        String word = Arrays.asList(array).stream().map(x -> x.replace("$","").toUpperCase(Locale.ROOT))
                .collect(Collectors.joining(" "));
        view.addObject("dataList", result);
        view.addObject("word", word);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("matakuliah/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MataKuliahModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        MataKuliahModel MataKuliah = this.service.getById(id);
        if(MataKuliah == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        ModelAndView view = new ModelAndView("matakuliah/edit.html");
        view.addObject("data", MataKuliah);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MataKuliahModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        MataKuliahModel MataKuliah = this.service.getById(id);
        if(MataKuliah == null){
            return new ModelAndView("redirect:/matakuliah");
        }

        ModelAndView view = new ModelAndView("matakuliah/detail.html");
        view.addObject("data", MataKuliah);
        return view;
    }
}

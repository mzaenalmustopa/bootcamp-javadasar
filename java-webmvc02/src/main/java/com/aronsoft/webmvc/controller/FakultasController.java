package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.FakultasModel;
import com.aronsoft.webmvc.service.FakultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView view = new ModelAndView("fakultas/index.html");
        List<FakultasModel> result = service.get();

        String[] array = new String[]{"B$u$i$ld", "$t$$h$e", "N$e$x$t", "E$$ra", "$$o$f$", "S$$of$t$wa$r$e", "De$$ve$l$op$me$n$t"};
        String word = Arrays.asList(array).stream().map(x -> x.replace("$","").toUpperCase(Locale.ROOT))
                .collect(Collectors.joining(" "));
        view.addObject("dataList", result);
        view.addObject("word", word);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("fakultas/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute FakultasModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        FakultasModel fakultas = this.service.getById(id);
        if(fakultas == null){
            return new ModelAndView("redirect:/fakultas");
        }

        ModelAndView view = new ModelAndView("fakultas/edit.html");
        view.addObject("data", fakultas);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute FakultasModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/fakultas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        FakultasModel fakultas = this.service.getById(id);
        if(fakultas == null){
            return new ModelAndView("redirect:/fakultas");
        }

        ModelAndView view = new ModelAndView("fakultas/detail.html");
        view.addObject("data", fakultas);
        return view;
    }
}

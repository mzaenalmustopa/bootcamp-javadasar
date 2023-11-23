package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.GedungModel;
import com.aronsoft.webmvc.service.GedungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gedung")
public class GedungController {
    private GedungService service;

    @Autowired
    public GedungController(GedungService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("gedung/index.html");
        List<GedungModel> result = service.get();

        String[] array = new String[]{"B$u$i$ld", "$t$$h$e", "N$e$x$t", "E$$ra", "$$o$f$", "S$$of$t$wa$r$e", "De$$ve$l$op$me$n$t"};
        String word = Arrays.asList(array).stream().map(x -> x.replace("$","").toUpperCase(Locale.ROOT))
                .collect(Collectors.joining(" "));
        view.addObject("dataList", result);
        view.addObject("word", word);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("gedung/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute GedungModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        GedungModel Gedung = this.service.getById(id);
        if(Gedung == null){
            return new ModelAndView("redirect:/gedung");
        }

        ModelAndView view = new ModelAndView("gedung/edit.html");
        view.addObject("data", Gedung);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute GedungModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        GedungModel Gedung = this.service.getById(id);
        if(Gedung == null){
            return new ModelAndView("redirect:/gedung");
        }

        ModelAndView view = new ModelAndView("gedung/detail.html");
        view.addObject("data", Gedung);
        return view;
    }
}

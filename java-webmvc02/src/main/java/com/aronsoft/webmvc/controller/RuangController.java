package com.aronsoft.webmvc.controller;

import com.aronsoft.webmvc.model.RuangModel;
import com.aronsoft.webmvc.service.RuangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ruang")
public class RuangController {
    private RuangService service;

    @Autowired
    public RuangController(RuangService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("ruang/index.html");
        List<RuangModel> result = service.get();

        String[] array = new String[]{"B$u$i$ld", "$t$$h$e", "N$e$x$t", "E$$ra", "$$o$f$", "S$$of$t$wa$r$e", "De$$ve$l$op$me$n$t"};
        String word = Arrays.asList(array).stream().map(x -> x.replace("$","").toUpperCase(Locale.ROOT))
                .collect(Collectors.joining(" "));
        view.addObject("dataList", result);
        view.addObject("word", word);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("ruang/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute RuangModel request){
        this.service.save(request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        RuangModel Ruang = this.service.getById(id);
        if(Ruang == null){
            return new ModelAndView("redirect:/ruang");
        }

        ModelAndView view = new ModelAndView("ruang/edit.html");
        view.addObject("data", Ruang);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute RuangModel request){
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        RuangModel Ruang = this.service.getById(id);
        if(Ruang == null){
            return new ModelAndView("redirect:/ruang");
        }

        ModelAndView view = new ModelAndView("ruang/detail.html");
        view.addObject("data", Ruang);
        return view;
    }
}

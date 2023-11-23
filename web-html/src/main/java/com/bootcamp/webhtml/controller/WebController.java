package com.bootcamp.webhtml.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class WebController {

    @GetMapping("/home")
    public ModelAndView index(){
        return new ModelAndView("pages/home/index");
    }

    @GetMapping("/contact")
    public ModelAndView contact(){
        return new ModelAndView("pages/contact/index");
    }

    @GetMapping("/about")
    public ModelAndView about(){
        return new ModelAndView("pages/about/index");
    }

    @GetMapping("/element")
    public ModelAndView element(){
        return new ModelAndView("pages/elements/attribute");
    }

    @GetMapping("/style")
    public ModelAndView style(){
        return new ModelAndView("pages/elements/style");
    }
}
